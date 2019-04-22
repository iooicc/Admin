package my.admin.framework.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import my.admin.framework.util.UserUtil;
import my.admin.model.SysFile;
import my.admin.model.SysUser;
import my.common.config.Global;
import my.common.beans.Result;
import my.common.idgen.IdGenerate;

@Controller
@RequestMapping({ "/file" })
public class FileController {
	
	@Autowired
	SQLManager sqlManager;
	
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public Result upload(
		@RequestPart(value = "file") MultipartFile multipartFile,
    	@RequestParam(value = "folder", defaultValue = "") String folder,
    	@RequestParam(value = "isStep", defaultValue = "0") Integer isStep,
    	@RequestParam(value = "isThumb", defaultValue = "0") Integer isThumb,
    	
    	@RequestParam(value = "boId", required = false) String boId,
    	@RequestParam(value = "boType", required = false) String boType
	) {
		Result result = new Result();
		OutputStream out = null;
		try {
			if(multipartFile==null) return result.setError("文件为空");
			if(multipartFile.getInputStream()==null) return result.setError("文件大小为空");
			
			String ext = FileUtil.extName(multipartFile.getOriginalFilename());
			
			Date now = new Date();
			String basePath = Global.getProperty("project.upload.savePath");
			String filePath = new SimpleDateFormat("yyyyMM").format(now)+"/";
			if(!"".equals(folder)) {
				filePath = folder;
			}
			
			filePath += new SimpleDateFormat("yyyyMMddHHmmss").format(now) + "_" + new Random().nextInt(1000) + "." + ext;
			
			java.io.File dest = new java.io.File(basePath, filePath);
			if(!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			
			if(!dest.exists()) {
				dest.createNewFile();
			}
			
			out = new FileOutputStream(dest);
			IoUtil.copy(multipartFile.getInputStream(), out);
			SysUser user = UserUtil.getUser();
			
			SysFile file = new SysFile();
			file.setFileId(IdGenerate.nextId());
			file.setFileName(multipartFile.getOriginalFilename());
			file.setFileExt(ext);
			file.setFileType(multipartFile.getContentType());
			file.setFilePath(filePath);
			file.setCreateBy(user.getUserCode());
			file.setCreateDate(now);
			file.setBoId(boId);
			file.setBoType(boType);
			file.setIsStep(isStep);
			file.setIsThumb(isThumb);
			file.setFileSize(FileUtil.size(dest));
			
			sqlManager.insertTemplate(file, true);
			
			file.set("url", Global.getProperty("project.download.urlPrefix"));
			
			result.setData(file);
			return result;
		} catch (Exception e) {
			return result.setError(e.getMessage());
		} finally {
			IoUtil.close(out);
		}
	}
	
	@GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(
		@RequestParam(value = "fileId") String fileId
    ) {
    	HttpHeaders headers = new HttpHeaders();
    	InputStream in = null;
    	try {
    		if(fileId==null) {
    			return new ResponseEntity<byte[]>("没有找到文件[-1]".getBytes(), headers, HttpStatus.OK);
    		}
    		
    		SysFile file = sqlManager.single(SysFile.class, fileId);
    		if(null==file) {
    			return new ResponseEntity<byte[]>("没有找到文件[-2]".getBytes(), headers, HttpStatus.OK);
    		}
    		
    		String basePath = Global.getProperty("project.upload.savePath");
    		File target = new File(basePath, file.getFilePath());
    		if(!target.exists()) {
    			return new ResponseEntity<byte[]>("没有找到文件[-3]".getBytes(), headers, HttpStatus.OK);
    		}
    		
    		String fileName=new String(file.getFileName().getBytes("UTF-8"), "iso-8859-1");
    		headers.setContentDispositionFormData("attachment", fileName); 
    		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    		in = new FileInputStream(target);
    		
    		return new ResponseEntity<byte[]>(IoUtil.readBytes(in), headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<byte[]>("没有找到文件[-99]".getBytes(), headers, HttpStatus.OK);
		} finally {
			IoUtil.close(in);
		}
	}
	
	@RequestMapping({ "listData" })
	@ResponseBody
	public List listData(
		SysFile row
	) {
		List list = sqlManager.template(row);
		return list;
	}
	
	@PostMapping({ "delete" })
	@ResponseBody
	public Result delete(SysFile row) {
		String fileId = null;
		if(row!=null
			&& (fileId=row.getFileId())!=null
			&& !StrUtil.isBlank(fileId)
		) {
			SysFile file = sqlManager.single(SysFile.class, fileId);
			if(file!=null) {
				sqlManager.deleteById(SysFile.class, fileId);
				FileUtil.del(Global.getProperty("project.upload.savePath")+file.getFilePath());
			}
		}
		return Result.SUCCESS;
	}
	
	@PostMapping({ "isStep" })
	@ResponseBody
	public Result isStep(SysFile row) {
		Map<String, Object> paras = MapUtil.newHashMap();
		
		String fileId = null;
		if(row!=null
			&& (fileId=row.getFileId())!=null
			&& !StrUtil.isBlank(fileId)
		) {
			SysFile file = sqlManager.single(SysFile.class, fileId);
			if(file!=null) {
				String boId = file.getBoId();
				
				paras.put("boId", boId);
				paras.put("fileId", fileId);
				sqlManager.executeUpdate("update sys_file set is_step=0 where bo_id=#boId#", paras);
				sqlManager.executeUpdate("update sys_file set is_step=1 where file_id=#fileId#", paras);
			}
		}
		return Result.SUCCESS;
	}
	
	@PostMapping({ "isThumb" })
	@ResponseBody
	public Result isThumb(SysFile row) {
		Map<String, Object> paras = MapUtil.newHashMap();
		
		String fileId = null;
		if(row!=null
			&& (fileId=row.getFileId())!=null
			&& !StrUtil.isBlank(fileId)
		) {
			SysFile file = sqlManager.single(SysFile.class, fileId);
			if(file!=null) {
				String boId = file.getBoId();
				
				paras.put("boId", boId);
				paras.put("fileId", fileId);
				sqlManager.executeUpdate("update sys_file set is_thumb=0 where bo_id=#boId#", paras);
				sqlManager.executeUpdate("update sys_file set is_thumb=1 where file_id=#fileId#", paras);
			}
		}
		return Result.SUCCESS;
	}

}
