package com.lfy.lfy202120201124.entity;

import com.lfy.lfy202120201124.R;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    //模拟数据
    public static List<ProductInfo> getListData(int position){
        List<ProductInfo> list = new ArrayList<>();
        if (position == 0){
            list.add(new ProductInfo(1001, R.mipmap.hd820,
                    "SENNHEISER/森海塞尔 HD 820旗舰级头戴式耳机HIFI发烧封闭式耳机",
                    "HD 820打破了人们对封闭式耳机的预期,创新性的技术、" +
                            "独特的凹面玻璃,将换能器后方的声波反射到吸声体之中" +
                            "从而有效降低腔体中的共振，使其带来如开放式" +
                            "耳机一样的透明饱满的声音",
                    13010
            ));
            list.add(new ProductInfo(1002, R.mipmap.hd800s,
                    "SENNHEISER/森海塞尔HD 800S开放头戴旗舰高保真HIFI耳机发烧耳机",
                    "通过吸收共振能量，HD 800S采用森海塞尔创新的吸音技术可" +
                            "以防止出现意外的高音，并让音乐中的频率成分更好地诠释出" +
                            "来，即使是细微的差别也可以被听到。",
                    9250
            ));
            list.add(new ProductInfo(1003, R.mipmap.hd490pro,
                    "森海塞尔HD490PRO有线头戴式开放式专业耳机",
                    "由德国工艺打造，采用经久耐用的组件和轻巧灵活的" +
                            "设计，让您尽情感受音乐，忘记耳机的存在。开放式" +
                            "设计可提供极其宽广、立体的声场和超精确的声音再" +
                            "现效果，消除音频盲点，确保您完全掌控每个细节。",
                    5499));
            list.add(new ProductInfo(1004, R.mipmap.hd660s2,
                    "SENNHEISER/森海塞尔HD 660S2头戴式动圈耳机",
                    "具有精确声音重现的 HD 660S2 通过重低音的扩展带来了更强" +
                            "的冲击力，让音乐效果气势磅礴而且充满张力。森海塞尔音频" +
                            "工程师匠心调教，让耳机在最低的频段将声压几乎翻倍，低音" +
                            "的扩展为声音创造更多频域空间，在低音更深沉的背景衬托下" +
                            "高频的感觉更加明亮，声场更加宽广，情感表达更加强烈",
                    5299));
            list.add(new ProductInfo(1005, R.mipmap.hd650,
                    "SENNHEISER/森海塞尔HD 650经典头戴式耳机专业发烧监听耳机",
                    "采用高度优化的磁石系统，HD650 具有很低的谐波失真和精确" +
                            "的声音重放，可以覆盖整个频响范围。带来纯正的低音，平衡" +
                            "的中音和高音，确保了逼真的声音再现。",
                    4399));
            list.add(new ProductInfo(1006, R.mipmap.hd600,
                    "SENNHEISER/森海塞尔HD600开放式头戴高保真耳机",
                    "采用计算机辅助优化的磁铁和铝合金线圈，将谐波和互调" +
                            "失真降至很小，使其拥有天然、清脆、空间感的声音。",
                    2199));
            list.add(new ProductInfo(1007, R.mipmap.oprheus,
                    "森海塞尔 ORPHEUS大奥菲斯二代HE1旗舰级静电耳机系统",
                    "1991年，我们创造出传奇耳机奥菲斯。现在，我们续写神话。依然" +
                            "保留其毫不妥协的理念，这是一款浴火再燃的SENNHEISERHE1，" +
                            "具有脱俗的声音和经得起时间考验的经典之美。设计日臻优美。配" +
                            "备独特的功能和先进的技术，这款耳机把音乐从聆听的对象转变为您的生活方式。" +
                            "您可能从未在耳机中见识过这种美妙。但这只是开始。轻轻触" +
                            "摸一下，就能看到SENNHEISERHE1渐渐生动起来--短暂的" +
                            "停顿之后，使其功放达到合适的温度。然后戴上耳机享受纯粹" +
                            "的声音。让您如入无人之境。周围的环境在您的音乐中渐渐远" +
                            "去。无论是黑胶唱片、CD，还是高分辨率数字文件，SENNHE-" +
                            "ISERHE1都能带您进入纯粹的声音世界。通过从8到超过" +
                            "100,000HZ的频率响应范围，声音还原系统中测量到的低失" +
                            "真是0.01%(1KHZ，100DBSPL条件下)。",
                    499999));
        } else if (position==1) {
            list.add(new ProductInfo(2001, R.mipmap.ap2000ti,
                    "Audio Technica/铁三角 ATH-AP2000Ti 头戴式钛合金音乐HIFI耳机",
                    "Ø53mm 驱动单元，集结了坡曼德铁钴合金高磁通回路、类金刚石碳纤镀层振膜以及纯铁磁轭等技术于一身\n" +
                            "铁三角的中心安装技术 (CORE MOUNT Technology/PAT.P) 以及双重空气阻尼结构 (Double Air Damping System)，实现更宽广的频率响应\n" +
                            "音响优良的钛合金机壳\n" +
                            "铝镁合金支臂以及头梁滑轨，追求结构强度与轻量化\n" +
                            "兼具耐用性与舒适触感，小羊皮包覆低反弹材质的立体缝制耳垫\n" +
                            "高音质的A2DC 端子\n" +
                            "左右声道独立的星形四芯绞线，减轻左右声道串扰噪信\n" +
                            "随机附有能够提高左右声道分离度的 6N-OFC 双边出线 1.2米平衡连接耳机线 (Ø4.4mm的5极插头) 以及标准3.0米/1.2米耳机导线",
                    8999));
            list.add(new ProductInfo(2002, R.mipmap.wp900,
                    "Audio Technica/铁三角 ATH-WP900 新木碗头戴式便携HIFI发烧耳机",
                    "ATH-WP900仍采用木质外壳，挑选如火焰纹路和颜色的枫木制成" +
                            "因为质地坚硬，能够提供声音上的高度清晰感。这种木纹广受电吉他" +
                            "玩家的喜爱，满足年轻却追求质感的族群的诉求，其意图不言而喻。",
                    5999));
            list.add(new ProductInfo(2003, R.mipmap.ad2000x,
                    "Audio Technica/铁三角 ATH-AD2000X 头戴空气动圈游戏HiFi耳机",
                    "ATH-AD2000X的53mm驱动器，采用了波门杜尔铁钴合金和0FC-7N音圈，提供细致的音色重现\n" +
                            "空气扩散控制，使声音能够宽敞的散布\n" +
                            "双边式的四芯导线，左右声道均设以的地线连接\n" +
                            "挡板设有鳍状的气流通道，提高耐用性和轻巧的重里\n" +
                            "镁金属部件，提高了轻巧的设计\n" +
                            "3D耳机架的设计，提供舒适的支撑以达良好的聆听经验，全适耳设计，减少压力\n" +
                            "高耐用性和光滑质感的麂皮慢回弹泡棉耳垫\n" +
                            "高导电性的0FC-6N+OFC混合式导线设有弹性护套，并可避免纠结",
                    4330));
            list.add(new ProductInfo(2004, R.mipmap.r70x,
                    "Audio Technica/铁三角 ATH-R70X 开放式监听 HIFI音乐高阻抗耳机",
                    "采用了磁体以及一个纯合金的磁性电路设计，减少了失真，确保了准确" +
                            "性并延展了高频响应。使用了碳复合材料树脂改善了结构钢性，三维翼支撑" +
                            "系统上改进而来的支撑承托系统，两道金属外包塑料的设计变成一道金属凿" +
                            "孔的设计，就算需要长时间配戴也一样舒适。R70x耳机配备了良好的配戴感" +
                            "便利性和音色纯度，坚固而轻便的结构，并配有双边式可更换导线，L/R" +
                            "无区别,不管是如何连接，亦能自动保持适当的立体声左右定位。",
                    2480));
            list.add(new ProductInfo(2005, R.mipmap.m70x,
                    "Audio Technica/铁三角 ATH-M70X 旗舰款专业监听头戴式耳机",
                    "M系列耳机中的旗舰型号，采用金属零件细心打造，并对单元作出了的调整，以求高解析度音色\n" +
                            "搭载高磁力45毫米大直径单元和CCAW音圈，带来宽屏而平坦的频率响应\n" +
                            "具有很好的频响延伸范围，能再现高频和低频的范围信号，同时保持良好的三频平衡感\n" +
                            "全包裹式耳罩设计，即使在嘈杂的环境下亦能获得很好的隔音效果\n" +
                            "90度可旋转耳罩设计，使用方便，同时可作单耳式监听\n" +
                            "耳垫和头梁材料提供耐用性和舒适性\n" +
                            "适用于录音室的混音、调音、母带制作以及音频取证等工作\n" +
                            "平放式设计能节省空间，便于随身携带\n" +
                            "可分离式导线设计（包含1.2米- 3.0米长绕圈式导线、1.2 米平直式导线及 3.0米长平直式长导线）",
                    2280));
            list.add(new ProductInfo(2006, R.mipmap.m50x,
                    "铁三角ATH-M50x专业头戴式监听有线耳机",
                    "广受音响工程师和音频用家的青睐\n" +
                            "搭载高磁力45毫米大直径单元和CCAW音圈\n" +
                            "在整个扩展频率范围中有高的清晰度，并具深厚和紧致的低音响应\n" +
                            "全包裹式耳朵轮廓的耳罩设计，在嘈杂的环境中提供良好的隔音效果\n" +
                            "90度可旋转式耳罩设计，方便作单耳监听\n" +
                            "舒适且耐用的耳垫和头带材质，提升佩戴舒适性\n" +
                            "可折叠设计，能节省空间，便于随身携带\n" +
                            "可分离式导线设计（包含1.2米- 3.0米长绕圈式导线、1.2 米平直式导线及 3.0米长平直式长导线）\n" +
                            "录音棚录制、混音、DJ监听以及个人聆听的理想选择",
                    1199));
        } else if (position==2) {
            list.add(new ProductInfo(3001, R.mipmap.dt900prox,
                    "beyerdynamic拜雅DT900PROX头戴式HIFI耳机拜亚动力",
                    "DT 900 PRO X自然、准确、可靠的专业音质" +
                    "得益于研发在德国Heilbronn全新STELLAR.45" +
                    "声音传感器，全面研发的单元结构和精选材料，" +
                    "苛刻严谨的调音标准，全开放式的设计，使得" +
                    "DT 900 PRO X具备更为开阔的声场，宽广的频" +
                    "率响应，准确的结像力，高频细腻，中频饱满" +
                    "低频控制力充足，整体细节还原丰富。",
                    1899
            ));
            list.add(new ProductInfo(3002, R.mipmap.t5,
                    "beyerdynamic拜雅T5 三代头戴式耳机HiFi音质可拆卸导线32欧",
                    "新一代T5采用拜雅全新特斯拉音频技术单元，硬核的特斯" +
                            "拉创新技术能有效的降低腔体内部谐振，提供强大的磁驱" +
                            "动力，增加腔体的稳定性，从而提升声音的高保真度。T5" +
                            "可谓承载了beyerdynamic近百年历史文化的沉淀，在总体" +
                            "音质上更为中性、自然和宽阔，低频量感充沛丰富，中高" +
                            "频表现真实柔和，在人声和乐器演绎上，能真实展现每个" +
                            "声音的细节",
                    6599
            ));
            list.add(new ProductInfo(3003, R.mipmap.t1,
                    "beyerdynamic拜雅 T1三代 头戴式HIFI高保真HIFI有线耳机32Ω",
                    "拜雅新一代特斯拉旗舰耳机T1传承拜雅一贯精益求精的工" +
                            "艺，融合了拜雅在设计、音质、佩戴舒适等方面的出色表" +
                            "现，搭载全新的大动圈第三代特斯拉单元技术，拥有大声" +
                            "场，高密度，高解析，参考级的 HiFi音质，真实展现每一" +
                            "个声音的细节。",
                    7099
            ));
            list.add(new ProductInfo(3004, R.mipmap.dt700pro,
                    "beyerdynamic拜雅DT770 PRO头戴式封闭耳机拜亚动力",
                    "缩混录音室是对所录音乐进行平衡，润色，调整的地方。对于这样高质量要求的" +
                            "专业耳机，DT770pro保证对信号的解释达到高精确水准。封闭式结构使其对外" +
                            "界噪声可衰减16 dB，即使在扬声器前面，DT770 pro依然可以不受影响地聆听" +
                            "音乐。它的软耳垫可保证您长时间舒适地佩戴使用。",
                    1299
            ));
            list.add(new ProductInfo(3005, R.mipmap.dt700prox,
                    "beyerdynamic拜雅DT770 PRO X百年限定款头戴式录音室封闭式耳机",
                    "DT 770 PRO X限量版包耳式录音室耳机传承 DT 770 PRO 久经" +
                            "考验的声学特征。强劲的低音、细腻的高音搭配一流的脉冲性" +
                            "能。这些品质受到专业音乐家、制作人、录音室老师和音响工" +
                            "程师数十年来的信赖。灵活而强大的性能，配合封闭式设计，" +
                            "无论是录音还是监听，它都能呈现纯净、真实的声音世界。",
                    1799
            ));
            list.add(new ProductInfo(3006, R.mipmap.dt880pro,
                    "beyerdynamic拜雅DT880 PRO头戴式半开放式监听耳机",
                    "参考级、半开放式录音室耳机DT880 pro结合了开放式耳机和封闭式" +
                            "耳机的优势，平衡降低环境噪声，保证声音平滑自然，细腻演绎每个" +
                            "音符，高频部分清澈透明无杂音，中频精确具说服力，低音丰满而不" +
                            "夸张。",
                    1899
            ));
            list.add(new ProductInfo(3007, R.mipmap.dt1990pro,
                    "beyerdynamic拜雅DT1990 PRO 特斯拉单元头戴式旗舰耳机250Ω",
                    "特斯拉技术是拜雅享有的耳机单元技术，" +
                            "采用由钕制成的非常坚固的环形磁铁以及对换能器的一系列优化，" +
                            "使声音更清洁,透明,宽广 并且换能效率显著提高，" +
                            "这在具有低声压级的播放设备上特别有用。" +
                            "此外在相同的条件下,失真更少,表现出更宽的频率响应,声音更精确。" +
                            "DT1990 pro 采用了特斯拉单元全面改进了单元结构和材料" +
                            "带来卓越的低频细节与控制力，和更饱满的中频、更柔顺的高频。",
                    4299
            ));

        }else if (position ==3){
            list.add(new ProductInfo(4001, R.mipmap.z1r,
                    "Sony/索尼 MDR-Z1R立体声头戴式HiRes高解析度高音质Hifi发烧耳机",
                    "70mm大孔径HD驱动单元（声波变宽和平坦，体验到更自然的声音）\n" +
                            " 镁质球顶振膜（实现120kHz高频率范围的声音再现。）\n" +
                            " 斐波那契隔栅（驱动单元开孔均衡，硬度高，高频平稳）\n " +
                            "人体工程学皮革耳垫（触感柔和，佩戴舒适。）\n" +
                            " 皮革头箍（耐用柔软，佩戴舒适）\n" +
                            " 听感：声音清澈，音质辽远，自然静谧，声场开阔精准，高频延展，自然，空气感十足。\n" +
                            " 适合音乐类型，大编制，古典，流行，民谣，摇滚。",
                    12999));
            list.add(new ProductInfo(4002, R.mipmap.wh1000xm5,
                    "Sony/索尼 WH-1000XM5 高解析度无线降噪头戴耳机",
                    "WH-1000XM5搭载双芯片系统，8麦克风系统" +
                            "为您主动大幅减少交通噪声、城市喧器，更能提升中高" +
                            "频段交谈声的降噪能力" +
                            "让您尽情沉浸于美妙的音乐世界。",
                    2999));
            list.add(new ProductInfo(4003, R.mipmap.mv1,
                    "Sony/索尼 MDR-MV1 专业开放式监听耳机 轻量化设计",
                    "通过精心设计的开放式声学结构，MDR-MV1" +
                            "可以精准复现音乐录制现场的空间感和声音细节表现。" +
                            "从创作者角度出发，这款专业监听耳机能够支" +
                            "持空间音频，高解析度立体声的混音与母带制" +
                            "作。助力音乐创作者的同时，也同样适合音乐" +
                            "发烧友使用，感受真实之美。",
                    2999));
            list.add(new ProductInfo(4004, R.mipmap.mdr7506,
                    "Sony/索尼 MDR-7506 专业监听耳机立体声音质全封闭隔音",
                    "自1991年发布以来，经过三十余年专业市场验证" +
                            "MDR-7506的音质与工艺依然出众。" +
                            "封闭式设计具有良好的隔音效果，能够适应各类工作" +
                            "场景。三频均衡定位精准，能够清晰细腻复现真实录" +
                            "制现场，在全球范围内被广泛应用于音乐录制、影视" +
                            "创作、电台播音等专业领域，广受好评。",
                    799));
        }else {
            list.add(new ProductInfo(5001, R.mipmap.k19,
                    "FiiO/飞傲 K19旗舰台式解码耳放DSP无损DSD耳机功率放大器一体机",
                    "31段专业级高精度无损PEO|ESS旗舰8通道DAC芯片ES9039SPRO*2\n" +
                            "8000mW+8000mW强悍推力\n" +
                            "8路THXAAA788+高性能超低相噪飞秒晶振\n" +
                            "全局时钟同源管理全平衡音频架构支持HDMI\n" +
                            "ARC解码",
                    8999));
            list.add(new ProductInfo(5002, R.mipmap.q7,
                    "FiiO/飞傲 Q7大功率便携台放THX AAA 788+全平衡架构DSD解码耳放",
                    "3W输出功率THXAAA 788+全平衡架构耳放台机/便携双供电模式\n" +
                            "兼容广泛接口高端配置 XMOSXU316+ES9038PRO+OCC5124\n" +
                            "自研数字核心|新一代机甲风设计|彩色IPS显示屏多重智能保护",
                    4999));
            list.add(new ProductInfo(5003, R.mipmap.r7,
                    "FiiO/飞傲 R7桌面高清数播解码耳放一体机DSD音乐播放器数字转盘",
                    "齐全输入输出接口|四档输出模式|支持Roon Ready等八种工作模式\n" +
                            "DC/AC双供电IES9068AS+THXAAA788+*2 音频架构|五档增益模式\n" +
                            "3.6W大功率|竖屏流媒体数播设计|高通660+Android 10l自定义RGB灯\n" +
                            "独立网口设计|个性系统屏保|专属DP模式I双USB口设计",
                    4999));
            list.add(new ProductInfo(5004, R.mipmap.k9,
                    "FiiO/飞傲K9Pro台式耳放DSD解码一体机K9AKM耳机功率放大器解码器",
                    "高性能DAC ES9038PRO*2THXAAA788+技术|双模双飞秒时钟\n" +
                            "QCC5124蓝牙芯片|支持LDAC/aptXAdaptive/aptXHD等蓝牙编码\n" +
                            "全平衡电路架构双电压线性变压器广泛接口兼容|APP智慧互联",
                    2549));
            list.add(new ProductInfo(5005, R.mipmap.q15,
                    "FiiO/飞傲 Q15蓝牙DSD苹果iPhone电脑便携耳放手机HIFI解码一体机",
                    "台机/便携双供电模式[1600mW大推力|蓝牙/USB/同轴多合一解码\n" +
                            "AKM旗舰DAC组合 AK4191EQ+AK4499EX高端配置 QCC5125+XU316\n" +
                            "全彩IPS显示屏|全局PEQ调节15级音频架构全方位保护",
                    2699));
            list.add(new ProductInfo(5006, R.mipmap.ka17,
                    "FiiO/飞傲 KA17解码小尾巴手机HIFI耳放迷你台放耳机功率放大器2x",
                    "台机模式 650mW+650mW强劲推力\n" +
                            "双通道旗舰DACES90690*2|十段高精度PEQ调节\n" +
                            "专业级XMOS16核芯XU316|四路全平衡 THX AAA 78+耳放\n" +
                            "多级HiFi音频架构数模分板防串扰设计三级十路精密供电设计\n" +
                            "人性化点阵屏交互系统独立Type-C供电接口",
                    999));
            list.add(new ProductInfo(5007, R.mipmap.m17,
                    "FiiO/飞傲 M17便携台机高清无损安卓音乐播放器WIFI蓝牙MQA耳放",
                    "双ES9038PRO双THXAAA788+双供电系统双模音量控制\n" +
                            "高通QCC5124XMOSXUF208超级电容5.99英寸incell显示屏\n" +
                            "五种输出接口双TypeC接口 9200mAh电池容量RGB氛围灯",
                    9999));
        }
        return list;
    }
}
