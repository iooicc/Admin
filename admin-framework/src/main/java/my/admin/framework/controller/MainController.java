package my.admin.framework.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Controller
public class MainController {
	
	@RequestMapping({"/global.min.js"})
	@ResponseBody
	public String globalJs(HttpServletRequest request, Model model) {
		StringBuilder builder = new StringBuilder();
		builder.append("ctx='").append(request.getContextPath()).append("'");
		builder.append(",ctxPath='").append(request.getContextPath()).append("'");
		builder.append(",ctxAdmin='").append(request.getContextPath()).append("'");
		builder.append(",ctxStatic='").append(request.getContextPath()).append("/static'");
		builder.append(",Global={TRUE:'true', STATUS_DISABLE:'2', STATUS_NORMAL:'0'}");
		
		return builder.toString();
	}
	
	@RequestMapping({"/qr"})
	public void qr(
		String data
		, @RequestParam(defaultValue="250") int w
		, @RequestParam(defaultValue="250") int h
		, HttpServletRequest request
		, HttpServletResponse response
	) throws IOException, WriterException {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, w, h, hints);
		
		String fileName=new String("qr.png".getBytes("UTF-8"), "iso-8859-1");
//		response.setContentType("application/octet-stream");
		response.setContentType("image/png");
		response.setHeader("Content-Disposition","attachment;filename="+fileName);
		
		
		MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
	}
	
	@RequestMapping({"/barCode"})
	public void barCode(
		String data
		, @RequestParam(defaultValue="250") int w
		, @RequestParam(defaultValue="50") int h
		, HttpServletRequest request
		, HttpServletResponse response
	) throws IOException, WriterException {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.CODE_128, w, h, hints);
		MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
	}
}
