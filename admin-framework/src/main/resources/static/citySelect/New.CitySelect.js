(function($) {
    $.fn.SnAddress = function(options, datas) {
        return this.each(function() {
            var $this = $(this);
            var opts = $.extend({},
            $.fn.SnAddress.defaults, options);
            var methord = {
                init: function($this, options) {
                    this.options = options;
                    this.element = $this;
                    var g = this,
                    p = this.options;
                    g.element.addClass("citySelect");
                    g.dom = g.dom || g._dom();
                    g.selected = [],
                    g.records = [],
                    g.rows = [];
                    p.datas = datas || p.datas;
                    if (p.columns.length < p.datas.length) {
                        return
                    }
                    g.renderHeader();
                    g.setEvent()
                },
                setEvent: function() {
                    var g = this,
                    p = this.options;
                    g.dom["cityboxbtn"].click(function(event) {
                        g.dom["cityboxbtn"].addClass("select");
                        g.dom["citybox"].show().css("top", g.inputHeight + "px");
                        if ($("iframe", g.element).length > 0) {
                            $("iframe", g.element).css({
                                top: g.inputHeight + "px",
                                height: g.dom["citybox"].height()
                            })
                        }
                        g.dom["chooseArea"].children("p").each(function() {
                            if ($(this).hasClass("disable") && $(this).hasClass("cur")) {
                                g.active_count = 0;
                                g.getData(g._getParam(0))
                            }
                        });
                        return event.stopPropagation()
                    });
                    g.dom["closeSelector"].click(function(event) {
                        g.reset();
                        return event.stopPropagation()
                    });
                    g.dom["chooseArea"].children("p").click(function(event) {
                        if ($(this).hasClass("disable")) {
                            return event.stopPropagation()
                        } else {
                            g.active_count = $(this).index();
                            g.getData(g._getParam(g.active_count));
                            return event.stopPropagation()
                        }
                    });
                    g.dom["cityshow"].on("click", "span>a",
                    function(event) {
                        var rowid = $(this).attr("data").split("_")[1];
                        g.select(g.records[rowid]);
                        if (p.stepMet != null && g.selected.length != p.datas.length) {
                            p.stepMet(g.selected, g.active_count, g)
                        }
                        g.changeVolume();
                        return event.stopPropagation()
                    });
                    $(document).click(function() {
                        if (g.dom["citybox"].css("display") == "block") {
                            g.reset()
                        }
                    })
                },
                changeVolume: function() {
                    var g = this,
                    p = this.options;
                    var _data = g.selected[g.active_count];
                    $("span", g.dom["chooseArea"]).eq(g.active_count).html(_data.name);
                    g.selected[g.active_count].isdirect = _data.item ? true: false;
                    var _cur = g.active_count;
                    for (var i = g.active_count + 1; i < p.datas.length; i++) {
                        $("span", g.dom["chooseArea"]).eq(i).html(p.columns[i].text)
                    }
                    if (g.active_count + 1 <= p.datas.length - 1) {
                        g.active_count++;
                        var _next = g.active_count;
                        if (_data.item) {
                            _cur = g.active_count;
                            $("p", g.dom["chooseArea"]).eq(g.active_count).addClass("disable");
                            $("span", g.dom["chooseArea"]).eq(g.active_count).html(_data.item[0].name);
                            g.selected[g.selected.length] = _data.item[0];
                            g.active_count++;
                            _next = g.active_count
                        }
                        if (g.active_count <= p.datas.length - 1) {
                            g.getData(g._getParam(_next))
                        } else {
                            g.active_count = 0;
                            g.setHeadVal();
                            g.reset()
                        }
                    } else {
                        g.setHeadVal();
                        g.reset()
                    }
                },
                getData: function(_param) {
                    var g = this,
                    p = this.options;
                    $("p", g.dom["chooseArea"]).eq(g.active_count).removeClass("disable").addClass("cur").siblings("p").removeClass("cur");
                    for (var i = g.active_count + 1; i < p.datas.length; i++) {
                        if ($("span", g.dom["chooseArea"]).eq(i).html() == p.columns[i].text) {
                            $("p", g.dom["chooseArea"]).eq(i).addClass("disable")
                        }
                    }
                    g.getAjax(_param)
                },
                getAjax: function(param) {
                    var g = this,
                    p = this.options;
                    var ajaxOptions = {
                        type: "GET",
                        url: p.url,
                        data: param,
                        dataType: "jsonp",
                        jsonp: "callback",
                        beforeSend: function(msg) {
                            g.dom["cityshow"].html("加载中...")
                        },
                        success: function(items) {
                            g.dom["cityshow"].html("");
                            g.records.length = 0;
                            g.rows.length = 0;
                            if (items.data.length == 1 && param.state == "town") {
                                g.records.push(0);
                                g.rows.push(items.data[0]);
                                g.selected[(g.selected.length == 4 ? 3 : g.selected.length)] = g.rows[parseInt(0)];
                                $("p", g.dom["chooseArea"]).eq(g.active_count).addClass("disable");
                                $("span", g.dom["chooseArea"]).eq(g.active_count).html(items.data[0].name);
                                g.active_count = 0;
                                g.setHeadVal();
                                g.reset()
                            } else {
                                $.each(items.data,
                                function(count, item) {
                                    var b = $("<span/>");
                                    var a = $("<a/>");
                                    if (item.ishot == "true") {
                                        a.addClass("imp")
                                    }
                                    a.attr("data", "Sns_" + count).html(item.name);
                                    b.append(a);
                                    g.dom["cityshow"].append(b);
                                    g.records.push(count);
                                    g.rows.push(item)
                                });
                                if ($("iframe", g.dom["citybox"]).length > 0) {
                                    $("iframe", g.dom["citybox"]).height(g.element[0].offsetHeight)
                                }
                            }
                        },
                        error: function(e1, e2) {}
                    };
                    $.ajax(ajaxOptions)
                },
                setHeadVal: function() {
                    var g = this,
                    p = this.options;
                    $.each(g.selected,
                    function(count, item) {
                        $("span", g.dom["cityboxbtn"]).eq(count).show().html(item.name);
                        if (item.isdirect || p.columns[count].hide) {
                            if (p.datas.length != 1) {
                                $("span", g.dom["cityboxbtn"]).eq(count).hide()
                            }
                        }
                    });
                    p.datas.length = 0;
                    $.each(g.selected,
                    function(count, item) {
                        p.datas.push(item)
                    });
                    if (p.complete != null) {
                        p.complete(p.datas, false)
                    }
                },
                renderHeader: function() {
                    var g = this,
                    p = this.options;
                    function _buildHtml(_count, items, isdisable) {
                        var _tempfun1 = function(_count, item, isdisable) {
                            var _html = "";
                            var _txt = $.trim(item.name) == "" ? p.columns[_count].text: item.name;
                            _html += "<span ";
                            if (item.isdirect || p.columns[_count].hide) {
                                _html += 'style="display: none"'
                            }
                            if (p.columns[_count].addclass) {
                                _html += ' class="' + p.columns[_count].addclass + '"'
                            }
                            _html += ">" + _txt + "</span>";
                            _html += "<em></em>";
                            return _html
                        };
                        var _tempfun2 = function(count, item, isdisable) {
                            var _txt = $.trim(item.name) == "" ? p.columns[_count].text: item.name;
                            var _html = '<p eq="' + count + '"><span>' + _txt + "</span><b></b></p>";
                            if (isdisable || item.id == "") {
                                _html = '<p class="disable"><span>' + _txt + "</span><b></b></p>"
                            }
                            return _html
                        };
                        g.dom["cityboxbtn"].append(_tempfun1(_count, items, isdisable));
                        g.dom["chooseArea"].append(_tempfun2(_count, items, isdisable))
                    }
                    if (p.datas && p.columns && p.datas.length > 0) {
                        var _isdirect = false;
                        $.each(p.datas,
                        function(count, item) {
                            if (item.id) {
                                g.selected[count] = item
                            }
                            _buildHtml(count, item, _isdirect);
                            _isdirect = p.datas[count].isdirect
                        });
                        if (g.selected.length > 0 && !g.selected[g.selected.length - 1].isdirect) {
                            if (g.selected.length != p.datas.length) {
                                g.active_count = g.selected.length;
                                g.getData(g._getParam(g.active_count))
                            } else {
                                g.active_count = g.selected.length - 1;
                                g.getData(g._getParam(g.active_count))
                            }
                        } else {
                            g.active_count = 0;
                            g.getData(g._getParam(g.active_count))
                        }
                        _isdirect = false;
                        if (p.complete != null) {
                            p.complete(p.datas, true)
                        }
                    }
                    g.dom.chooseArea["append"]('<div class="clear"></div>');
                    g.dom.cityboxbtn["append"]("<b></b>");
                    g.inputHeight = g.dom.cityboxbtn["height"]();
                    g.dom["chooseArea"].addClass("fix");
                    var _isIE6 = window.VBArray && !window.XMLHttpRequest;
                    if (_isIE6 && $("iframe", g.dom["citybox"]).length < 1) {
                        var iframe = document.createElement("iframe");
                        g.dom["citybox"].after(iframe);
                        var httpType = ("https:" == document.location.protocol) ? "https": "http";
                        if (httpType == "https") {
                            $(iframe).attr("src", "https://imgssl.suning.com/images/ShoppingArea/Common/blankbg.gif")
                        }
                        $(iframe).css({
                            width: g.dom["citybox"].width(),
                            height: 0,
                            position: "absolute",
                            "z-index": 10,
                            opacity: 0,
                            top: 0,
                            left: 0
                        })
                    }
                },
                reset: function() {
                    var g = this,
                    p = this.options;
                    g.dom["cityboxbtn"].removeClass("select");
                    g.dom["citybox"].hide();
                    if ($("iframe", g.element).length > 0) {
                        $("iframe", g.element).css({
                            height: 0 + "px"
                        })
                    }
                },
                select: function(rp) {
                    var g = this,
                    p = this.options;
                    var row = g.rows[parseInt(rp)];
                    g.selected.length = g.active_count;
                    g.selected[g.selected.length] = row
                },
                _getParam: function(rp) {
                    var g = this,
                    p = this.options;
                    var defaults = {
                        state: p.columns[rp].state,
                        selectId: rp - 1 > -1 ? g.selected[rp - 1].id: 1
                    };
                    var setting = {};
                    if (p.otherParam) {
                        var obj = p.otherParam(rp, p.columns, g.selected);
                        if (obj && typeof(obj) == "object") {
                            setting = obj
                        }
                    }
                    var params = $.extend(defaults, setting);
                    return params
                },
                _dom: function() {
                    var g = this,
                    p = this.options;
                    g.element.html(p.innerHtml);
                    var wrap = g.element;
                    var name, DOM = {
                        wrap: $(wrap)
                    },
                    els = wrap[0].getElementsByTagName("*"),
                    elsLen = els.length;
                    for (var i = 0; i < elsLen; i++) {
                        name = els[i].className;
                        if (name) {
                            DOM[name] = $(els[i], wrap)
                        }
                    }
                    return DOM
                },
                getAddress: function() {
                    var g = this,
                    p = this.options;
                    return p.datas
                },
                getRows: function() {
                    var g = this,
                    p = this.options;
                    return g.selected
                },
                setCurrent: function(rp) {
                    var g = this,
                    p = this.options;
                    g.dom["cityboxbtn"].addClass("select");
                    g.dom["citybox"].show().css("top", g.inputHeight + "px");
                    if (g.selected.length - parseInt(rp) >= 0) {
                        g.active_count = parseInt(rp);
                        g.getData(g._getParam(g.active_count))
                    } else {
                        g.active_count = 0;
                        g.getData(g._getParam(g.active_count))
                    }
                    return event.stopPropagation()
                }
            };
            methord.init($this, opts);
            $this.data("suning.address", methord)
        })
    };
    $.fn.SnAddress.defaults = {
        url: "http://www.suning.com/emall/SNAddressQueryCmd",
        innerHtml: ' <a href="javascript:void(0);"  class="cityboxbtn" ></a>' + '<div class="citybox">' + '<div class="chooseArea">' + "</div>" + '<div  class="arriveBox">' + '<div class="cityshow"></div>' + "</div>" + '<div class="closeSelector"></div>' + "</div>",
        level: 1,
        columns: [{
            state: "prov",
            text: "请选择省",
            hide: false,
            addclass: ""
        },
        {
            state: "city",
            text: "请选择市",
            hide: false,
            addclass: ""
        },
        {
            state: "area",
            text: "请选择区",
            hide: false,
            addclass: ""
        },
        {
            state: "town",
            text: "请选择乡镇",
            hide: false,
            addclass: ""
        }],
        datas: [{
            name: "",
            code: "",
            id: ""
        },
        {
            name: "",
            code: "",
            id: ""
        },
        {
            name: "",
            code: "",
            id: ""
        },
        {
            name: "",
            code: "",
            id: ""
        }],
        otherParam: null,
        stepMet: null,
        complete: null
    }
})(jQuery);