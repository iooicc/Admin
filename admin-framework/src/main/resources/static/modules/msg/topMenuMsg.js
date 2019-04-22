/*!
 * 消息推送实现
 * 
 */
$(function() {
    unreadMsg()
});
function unreadMsg() {
    $.get(ctx + "/msg/unreadMsg?__notUpdateSession=true&__t=" + new Date().getTime(),
    function(d) {
        var b = $("#msgList").empty();
        var a = d.count || 0,
        c = d.list || [];
        for (i = 0; i < c.length; i++) {
        	c[i].msgInner.sendDate=new Date(c[i].msgInner.sendDate).format("yyyy-MM-dd HH:mm:ss");
            b.append(js.template("msgListTpl", c[i]))
        }
        $("#msgNum, #msgNum2").text(a);
        $(".timeago").timeago();
        pullPoolMsg();
        if (window.ppmInt) {
            clearInterval(window.ppmInt)
        }
        window.ppmInt = setInterval(pullPoolMsg, 60 * 1000)
    })
}
function pullPoolMsg() {
    var a = $("#msgList");
    var b = a.attr("data-mergeMsgLimit");
    $.get(ctx + "/msg/pullPoolMsg?__notUpdateSession=true&__t=" + new Date().getTime(),
    function(e) {
    	//$("#msgList").empty();
        for (i = 0; i < e.length; i++) {
        	//格式化日期
        	e[i].msgInner.sendDate=new Date(e[i].msgInner.sendDate).format("yyyy-MM-dd HH:mm:ss");
            if (! (e.length > b)) {
                var g = js.template("msgTipTpl", e[i]);
                js.showMessage(g, e[i].msgInner.msgTitle, "info", 1000 * 60)
            }           
            if (e[i].id && e[i].id != "") {               
                a.prepend(js.template("msgListTpl", e[i]))
           }
            doFlashTitle()
        }
        if (e.length > b) {
            var f = {
                msgContentEntity: {
                    title: a.attr("data-mergeMsgTitle"),
                    content: js.text(a.attr("data-mergeMsgContent"), e.length)
                },
                sendDate: "",
                sendUserName: "",
                id: ""
            };
            var g = js.template("msgTipTpl", f);
            js.showMessage(g, null, "info", 1000 * 60)
        }
//        var c=0;
//        c += e.length || 0
//
//        $("#msgNum, #msgNum2").text(c);
//        $(".timeago").timeago()
        var c = parseInt($("#msgNum").text());
        if (!isNaN(c)) {
            c += e.length || 0
        } else {
            c = a.find("li").length
        }
        $("#msgNum, #msgNum2").text(c);
        $(".timeago").timeago()
        
    })
}

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


function readMsg(c, d, e) {
    var a = $(c).data("href");
    if (e == "") {
        a = ctx + "/msg/info?msgInnerRecordId="+e
    }
    var b = js.addTabPage($(c), d, a, true, false);
    if (b) {
        $("#" + b + "-frame").on("load",
        function() {
            setTimeout(unreadMsg, 1000)
        })
    }
}

var isWindowFocus = true;
if ("onfocusin" in document) {
    document.onfocusin = function() {
        isWindowFocus = true
    };
    document.onfocusout = function() {
        isWindowFocus = false
    }
} else {
    window.onfocus = function() {
        isWindowFocus = true
    };
    window.onblur = function() {
        isWindowFocus = false
    }
}
var flashStep = 0;
var flashTitleRun = false;
var normalTitle = document.title;
var flashTitle = function() {
    if (isWindowFocus) {
        document.title = normalTitle;
        flashTitleRun = false;
        return
    }
    flashTitleRun = true;
    flashStep++;
    if (flashStep == 3) {
        flashStep = 1
    }
    if (flashStep == 1) {
        document.title = "【消息】" + normalTitle
    }
    if (flashStep == 2) {
        document.title = "【　】" + normalTitle
    }
    setTimeout("flashTitle()", 500)
};
function doFlashTitle() {
    if (!flashTitleRun) {
        flashTitle()
    }
};