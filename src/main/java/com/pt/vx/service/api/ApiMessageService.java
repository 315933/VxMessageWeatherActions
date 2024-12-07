package com.pt.vx.service.api;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.stream.CollectorUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.pt.vx.config.KeyConfig;
import com.pt.vx.config.MainConfig;
import com.pt.vx.pojo.BirthDay;
import com.pt.vx.pojo.User;
import com.pt.vx.pojo.KeyDTO;
import com.pt.vx.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class ApiMessageService {

    private final SecureRandom random = new SecureRandom();

    public static List<KeyDTO> keyDTOS = new ArrayList<>();

    static {
        keyDTOS.add(KeyConfig.KEY_QING_HUA);
        keyDTOS.add(KeyConfig.KEY_DUAN_ZI);
        keyDTOS.add(KeyConfig.KEY_DU_JI_TANG);
        keyDTOS.add(KeyConfig.KEY_SENTENCE);
        keyDTOS.add(KeyConfig.KEY_MI_YU);
        keyDTOS.add(KeyConfig.KEY_HOROSCOPE);
        keyDTOS.add(KeyConfig.KEY_HISTORY_TODAY);
        keyDTOS.add(KeyConfig.KEY_XIN_GUAN);
    }
    public String getApiMessage(KeyDTO keyDTO, User user){
        String result = null;
        if (KeyConfig.KEY_QING_HUA.equalsKey(keyDTO)){
            result =  getQinghua();
        }else if (KeyConfig.KEY_DUAN_ZI.equalsKey(keyDTO)){
            result = getDuanZi();
        }else if (KeyConfig.KEY_DU_JI_TANG.equalsKey(keyDTO)){
            result = getDuJiTang();
        }else if (KeyConfig.KEY_SENTENCE.equalsKey(keyDTO)){
            result = getRandomSentence();
        }else if (KeyConfig.KEY_MI_YU.equalsKey(keyDTO)){
            result = getRiddle();
        }else if (KeyConfig.KEY_HOROSCOPE.equalsKey(keyDTO)){
            BirthDay[] birthDays = user.getBirthDays();
            if( Objects.nonNull(birthDays)){
                result = getHoroscope(birthDays[0]);
            }
        }else if (KeyConfig.KEY_HISTORY_TODAY.equalsKey(keyDTO)){
            result = getHistoryToday();
        }else if (KeyConfig.KEY_XIN_GUAN.equalsKey(keyDTO)){
            String address = user.getAddress().split("省",2)[0];
            result = getXinGuan(address);
        }
        log.info("随机API接口为：{},获取的结果为：{}",keyDTO.getKey(),result);
        return result;
    }

    /**
     *
     * @return 随机一个API访问
     */
    public KeyDTO getRandomKey(){
        List<KeyDTO> collect = keyDTOS.stream().filter(KeyDTO::isOpen).collect(Collectors.toList());
        if(collect.isEmpty()){
            return null;
        }
        int i = random.nextInt(collect.size());
        return collect.get(i);
    }

    /**
     * 随机情话
     *
     * @return
     */
    private String getQinghua() {
        String url = "https://api.oick.cn/dutang/api.php"; // 确保URL返回纯文本
        return HttpUtil.get(url);
    }

    /**
     * @return 获取段子
     */
    private String getDuanZi() {
        String url = "https://api.oick.cn/dutang/api.php"; // 确保URL返回纯文本
        return HttpUtil.get(url);
    }

    /**
     * @return 获取毒鸡汤
     */
    private String getDuJiTang() {
        String url = "https://api.oick.cn/dutang/api.php"; // 确保URL返回纯文本
        return HttpUtil.get(url);
    }

    /**
     * @return 随机一句
     */
    private String getRandomSentence() {
        String url = "https://api.oick.cn/dutang/api.php"; // 确保URL返回纯文本
        return HttpUtil.get(url);
    }

    /**
     * @return 谜语
     */
    private String getRiddle() {
        String url = "https://api.oick.cn/dutang/api.php"; // 确保URL返回纯文本
        return HttpUtil.get(url);
    }

    /**
     * @return 获取星座分析
     */
    private String getHoroscope(BirthDay birthDay) {
        String url = "https://api.oick.cn/dutang/api.php"; // 确保URL返回纯文本
        return HttpUtil.get(url);
    }

    /**
     * @return 获取历史上今天
     */
    private String getHistoryToday() {
        String url = "https://api.oick.cn/dutang/api.php"; // 确保URL返回纯文本
        return HttpUtil.get(url);
    }

    /**
     *
     * @param province 省份
     * @return 新冠信息
     */
    private  String getXinGuan(String province){
        String url = "https://api.oick.cn/dutang/api.php"; // 确保URL返回纯文本
        return HttpUtil.get(url);
    }



}
