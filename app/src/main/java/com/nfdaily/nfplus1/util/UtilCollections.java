package com.nfdaily.nfplus1.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author fengjingyu@foxmail.com
 *
 */
public class UtilCollections {

    /**
     * 判断list是不是空的
     */
    public static boolean isListBlank(List list) {

        if (list == null || list.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * 判断list是否可用
     */
    public static boolean isListAvaliable(List list) {

        if (list == null || list.isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * 判断一个Map集合是否为空
     *
     * @param map
     * @return true为是空
     */
    public static boolean isMapBlank(Map map) {

        if (map == null || map.size() == 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断一个Map集合是否为空
     *
     * @param map
     * @return true为是空
     */
    public static boolean isMapAvaliable(Map map) {

        if (map == null || map.size() == 0) {
            return false;
        }

        return true;
    }

    public static List getList(Object... args) {
        List list = new LinkedList();

        if (args != null && args.length > 0) {
            for (Object arg : args) {
                list.add(arg);
            }
        }
        return list;
    }

    public static Object getValueByName(Map map, String name) {
        try {

            Object result = null;

            if (map.containsKey(name)) {

                result = map.get(name);

            } else {

                Object[] values = map.values().toArray();
                for (Object obj : values) {

                    if (obj instanceof Map) {

                        result = getValueByName((Map) obj, name);
                        if (result != null) {
                            break;
                        }

                    } else if (obj instanceof List) {

                        result = getValueByName((List) obj, name);
                        if (result != null) {
                            break;
                        }

                    } else {
                        continue;
                    }
                }
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Object getValueByName(List list, String name) {

        try {
            Object result = null;
            for (Object obj : list) {

                if (obj instanceof Map) {

                    result = getValueByName((Map) obj, name);
                    if (result != null) {
                        break;
                    }

                } else if (obj instanceof List) {

                    result = getValueByName((List) obj, name);
                    if (result != null) {
                        break;
                    }

                } else {
                    continue;
                }
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
