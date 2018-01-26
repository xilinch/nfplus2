package com.nfdaily.nfplus1.cache;



/**
 * Created by zhangzhihao on 2017/11/23.
 */

public class INFCacheUtil {

//    /**
//     * @param content
//     * @param lastFileId
//     * @return
//     */
//    public static HashMap<String, Object> getColumnArticlesMap(String content, int lastFileId) {
//        HashMap<String, Object> map = new HashMap<>();
//        if (!TextUtils.isEmpty(content)) {
//            try {
//                JSONObject jsonObj = new JSONObject(content);
//                map.put("articles", jsonObj.opt("list"));
//                if (lastFileId == 0) {
//                    map.put("adv", jsonObj.opt("adv"));
//                }
//                map.put("nfhHomeRec", jsonObj.opt("nfhHomeRec"));
//                map.put("mySubNfhArticles", jsonObj.opt("mySubNfhArticles"));
//                map.put("version", "0");
//                map.put("hasMore", false);
//            } catch (JSONException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//        return map;
//    }
//
//    public static ArrayList<Column> getColumnsByAttId(int siteId, int parentColumnId) {
//        int counter = 0;
//        ArrayList<Column> columns = new ArrayList<Column>();
//        ArrayList<Column> deleteColumns = new ArrayList<Column>();
//        ArrayList<Column> tempCustomColumn = new ArrayList<Column>();
//        ArrayList<Column> tempCustomChosenColumn = getCustomChosenColumns(NFCacheManager.getInstance().getFileCache().readCache("chosenColumn_" + parentColumnId));
//        ArrayList<Column> tempCustomUnChosenColumn = getCustomUnChosenColumns(NFCacheManager.getInstance().getFileCache().readCache("unChosenColumn_" + parentColumnId));
//
//        ArrayList<Column> serviceColumn = getServiceColumns(ReaderApplication.getInstance(), NFCacheManager.getInstance().getFileCache().readCache(
//                getColumnFileName(siteId, parentColumnId)), siteId, parentColumnId);
//
//        ArrayList<Column> unShowColumn = new ArrayList<Column>();
//        ArrayList<Column> showColumn = new ArrayList<Column>();
//        Column c1 = new Column();
//        c1.setColumnId(-1);
//        c1.setColumnName("测试");
//        c1.setShowcolumn(true);
//        unShowColumn.add(0, c1);
//        // no use
//        for (int i = 0; i < serviceColumn.size(); i++) {
//            Column c = serviceColumn.get(i);
//            if (c.isShowcolumn()) {
//                unShowColumn.add(c);
//            } else {
//                showColumn.add(c);
//            }
//        }
//        // no use
//        if (unShowColumn.size() > 0) {
//            if (tempCustomUnChosenColumn != null
//                    && tempCustomUnChosenColumn.size() > 0) {
//                // 进到到此处，是说明用户有自己修改不显示和显示的栏目，所有不做处理，以用户修改为主
//            } else {
//                // 服务器上有栏目设置了不显示，第一次处理
//                tempCustomUnChosenColumn = unShowColumn;
//                tempCustomChosenColumn = showColumn;
//            }
//        }
//
//        tempCustomColumn.addAll(tempCustomUnChosenColumn);
//        if (tempCustomChosenColumn != null) {
//
//            tempCustomColumn.addAll(tempCustomChosenColumn);
//        }
//        LogUtil.d("getColumnsByAttId", "tempCustomColumn:" + tempCustomColumn);
//        if (serviceColumn.size() > 0) {
//            if (tempCustomColumn.size() > 0) {
//                if (serviceColumn.size() != tempCustomColumn.size() - 1) {
//                    // TODO
//                    // 如果服务上的栏目和本地的个数不同，就以服务器的为准，再把客户之前没有选择的栏目从serviceColumn里去掉
//                    if (tempCustomUnChosenColumn.size() > 0) {
//                        for (int n = 1; n < tempCustomUnChosenColumn.size(); n++) {
//                            for (int m = 0; m < serviceColumn.size(); m++) {
//                                if (tempCustomUnChosenColumn.get(n)
//                                        .getColumnId() == serviceColumn.get(m)
//                                        .getColumnId()) {
//                                    deleteColumns.add(serviceColumn.get(m));
//                                }
//                            }
//                        }
//                    }
//                    serviceColumn.removeAll(deleteColumns);
//                    columns = serviceColumn;
//                } else {
//                    for (int i = 0; i < serviceColumn.size(); i++) {
//                        for (int j = 1; j < tempCustomColumn.size(); j++) {
//                            // 添加判断栏目顶部轮播数量
//                            if (serviceColumn
//                                    .get(i)
//                                    .getColumnName()
//                                    .equals(tempCustomColumn.get(j)
//                                            .getColumnName())
//                                    && serviceColumn.get(i).getColumnType() == tempCustomColumn
//                                    .get(j).getColumnType()
//                                    && serviceColumn.get(i).getColumnTopNum() == tempCustomColumn
//                                    .get(j).getColumnTopNum()) {
//                                counter++;
//                                break;
//                            }
//                        }
//                    }
//                    // 更改这里判断逻辑,更新top album count
//                    if (counter <= serviceColumn.size()) {
//                        columns = serviceColumn;
//                    } else {
//                        // TODO 有的栏目改了名字或类型，把本地的栏目名字更新一下就可以了
//                        // TODO 更新客户选上的栏目
//                        for (int index = 0; index < tempCustomChosenColumn
//                                .size(); index++) {
//                            for (int index1 = 0; index1 < serviceColumn.size(); index1++) {
//                                if (serviceColumn.get(index1).getColumnId() == tempCustomChosenColumn
//                                        .get(index).getColumnId()) {
//                                    tempCustomChosenColumn.get(index)
//                                            .setColumnName(
//                                                    serviceColumn.get(index1)
//                                                            .getColumnName());
//                                    tempCustomChosenColumn.get(index)
//                                            .setColumnType(
//                                                    serviceColumn.get(index1)
//                                                            .getColumnType());
//                                    break;
//                                }
//                            }
//                        }
//                        // no user
//                        // TODO 更新客户没有选上的栏目
//                        for (int index = 1; index < tempCustomUnChosenColumn
//                                .size(); index++) {
//                            for (int index1 = 0; index1 < serviceColumn.size(); index1++) {
//                                if (serviceColumn.get(index1).getColumnId() == tempCustomUnChosenColumn
//                                        .get(index).getColumnId()) {
//                                    tempCustomUnChosenColumn.get(index)
//                                            .setColumnName(
//                                                    serviceColumn.get(index1)
//                                                            .getColumnName());
//                                    tempCustomUnChosenColumn.get(index)
//                                            .setColumnType(
//                                                    serviceColumn.get(index1)
//                                                            .getColumnType());
//                                    break;
//                                }
//                            }
//                        }
//                        columns = tempCustomChosenColumn;
//                    }
//                }
//            } else {
//                columns = serviceColumn;
//            }
//        } else {
//            columns = tempCustomChosenColumn;
//        }
//        String above = GsonUtils.arrayList2String(columns);
//        NFCacheManager.getInstance().getFileCache().writeCache("chosenColumn_" + parentColumnId, above);
//
//        String under = GsonUtils.arrayList2String(tempCustomUnChosenColumn);
//        NFCacheManager.getInstance().getFileCache().writeCache("unChosenColumn_" + parentColumnId, under);
//
//        return columns;
//    }
//
//    public static ArrayList<Column> getServiceColumns(Context context, String content, int siteId, int parentColumnId) {
//        ArrayList<Column> serviceColumn = new ArrayList<Column>();
//        JSONArray columnArray = new JSONArray();
//        long attIdVersion = 0;
//        int listSize = 0;
//
//        try {
//            if (!TextUtils.isEmpty(content)) {
//                JSONObject jsonObj = new JSONObject(content);
//                attIdVersion = jsonObj.getLong("version");
//                columnArray = (JSONArray) jsonObj.get("columns");
//            }
//            if (columnArray != null) {
//                listSize = columnArray.length();
//            }
//            if (listSize != 0) {
//                serviceColumn = new ArrayList<Column>();
//                for (int i = 0; i < listSize; i++) {
//                    JSONObject columnJson = (JSONObject) columnArray.get(i);
//                    Column column = new Column();
//                    column.setColumnId(columnJson.getInt("columnId"));
//                    column.setColumnName(columnJson.getString("columnName"));
//                    column.setPhoneIcon(columnJson.optString("phoneIcon", ""));
//                    column.setLinkUrl(columnJson.optString("linkUrl", ""));
//                    column.setColumnTopNum(columnJson.getInt("topCount"));
//                    column.setColumnType(columnJson.optInt("columnType", 0));
//                    column.setColumnStyle(columnJson.optInt("columnStyle", 0));
//                    //modify by xilinch 20161121
//                    column.setColTemplate(columnJson.optInt("colTemplate", 0) + "");
//                    //--end
//                    column.setOrderId(columnJson.optInt("orderId", 0));
//                    column.setColumnValue(columnJson.optString("columnvalue", ""));
//                    column.setShowcolumn(columnJson.optBoolean("noShowcolumn", false));
//                    column.setDescription(columnJson.optString("description", ""));
//                    serviceColumn.add(column);
//                }
//                // 更新一级栏目数据库
//                NewsColumnAttBean bean = new NewsColumnAttBean();
//                bean.setNewsColumnAttType(parentColumnId);
//                bean.setNewsColumnAttVersion(attIdVersion);
//                bean.setNewsSiteId(siteId);
//                upColumnVersionByType(context, bean, attIdVersion);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return serviceColumn;
//        }
//
//        return serviceColumn;
//    }
//
//    public static ArrayList<Column> getCustomUnChosenColumns(String content) {
//        ArrayList<Column> tempCustomColumn = new ArrayList<Column>();
//        if (!TextUtils.isEmpty(content)) {
//            try {
//                tempCustomColumn = GsonUtils
//                        .string2arrayList(content);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return tempCustomColumn;
//            }
//        }
//        return tempCustomColumn;
//    }
//
//    public static ArrayList<Column> getCustomChosenColumns(String content) {
//        ArrayList<Column> tempCustomColumn = new ArrayList<Column>();
//        try {
//            if (!TextUtils.isEmpty(content)) {
//                tempCustomColumn = GsonUtils
//                        .string2arrayList(content);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return tempCustomColumn;
//        }
//        return tempCustomColumn;
//    }
//
//    /**
//     * 更新一级栏目的版本号根据栏目类型
//     *
//     * @param context
//     * @param version 版本号
//     * @return
//     */
//    public static void upColumnVersionByType(Context context,
//                                             NewsColumnAttBean columnBean, long version) {
//        NewsColumnAttHelper newsColumnAttObj = new NewsColumnAttHelper(context);
//        newsColumnAttObj.open();
//        newsColumnAttObj.updataOrCreate(columnBean, version);
//        newsColumnAttObj.close();
//    }
//
//    /**
//     * 栏目稿件缓存名字 2.0
//     * 修改StringBuffer为StringBuilder
//     *
//     * @param columnId
//     * @param lastFileId
//     * @param count
//     * @return
//     */
//    public static String getColumnName(int columnId, int lastFileId, int count) {
//        StringBuilder nameBuilder = new StringBuilder();
//        nameBuilder.append(UrlConstants.URL_GET_COLUMN_ARTICLES).append("_columnId_").append(columnId)
//                .append("_lastFileId_").append(lastFileId).append("_count_")
//                .append(count);
//        return nameBuilder.toString();
//    }
//
//    /**
//     * 栏目的缓存文件名字
//     *
//     * @param siteId
//     * @param parentColumnId
//     * @return
//     */
//    public static String getColumnFileName(int siteId,
//                                           int parentColumnId) {
//        StringBuilder name = new StringBuilder();
//        name.append(UrlConstants.URL_GET_COLUMNS).append("_siteId_").append(siteId)
//                .append("_parentColumnId_").append(parentColumnId);
//        return name.toString();
//    }
//
//    /**
//     * 服务频道栏目的缓存文件名字
//     *
//     * @param siteId
//     * @param ColumnName
//     * @return
//     */
//    public static String getServiceColumnFileName(int siteId,
//                                                  String ColumnName) {
//        StringBuilder name = new StringBuilder();
//        name.append(UrlConstants.URL_GET_COLUMNS).append("_siteId_").append(siteId)
//                .append("_ColumnName_").append(ColumnName);
//        return name.toString();
//    }
//
//    /**
//     * 解析文章数据
//     *
//     * @param map
//     * @return
//     */
//    public static ArrayList<HashMap<String, String>> getColumnArticlesList(HashMap map) {
//        ArrayList<HashMap<String, String>> columnList = new ArrayList<HashMap<String, String>>();
//        try {
//            JSONArray advJsonList = null;
//            if (map != null && map.containsKey("adv")) {
//                advJsonList = (JSONArray) map.get("adv");
//            }
//            if (map != null && map.containsKey("articles")) {
//                JSONArray docsList = null;
//                if (map.get("articles") instanceof JSONArray) {
//                    docsList = (JSONArray) map.get("articles");
//                } else {
//                    docsList = new JSONArray((String) map.get("articles"));
//                }
//                if (docsList != null && docsList.length() > 0) {
//                    int listSize = docsList.length();
//                    for (int i = 0; i < listSize; i++) { //遍历所有的CMS稿件
//                        JSONObject jsonObj = new JSONObject(docsList.get(i)
//                                .toString());
//                        HashMap<String, String> columnsMap = new HashMap<String, String>();
//                        Iterator iter = jsonObj.keys();
//                        while (iter.hasNext()) {
//                            String key = iter.next().toString();
//                            String value = jsonObj.get(key).toString();
//                            if ("picBig".equals(key)) {
//                                value += ".2";
//                            }
//                            columnsMap.put(key, value);
//                        }
//                        if (jsonObj.has("templateID") && jsonObj.optInt("templateID") == 2) {
//                            //处理新版专题数据
//                            columnsMap.put("cusType", "3");
//                        }
//                        if (columnsMap.size() > 0) {
//                            String type = columnsMap.get("articleType");
//                            if (!"8".equals(type)) {
//                                //过滤掉香芋内发布的广告文
//                                columnList.add(columnsMap);
//                            }
//                        }
//                    }
//                    //广告系统发布的广告稿件
//                    if (advJsonList != null && advJsonList.length() > 0) {
//                        for (int i = 0; i < advJsonList.length(); i++) {
//                            JSONObject ad = (JSONObject) advJsonList.get(i);
//                            int index = ad.optInt("advsortno", 0);
//                            if (index > 0 && index <= columnList.size()) {
//                                index--;
//                                HashMap<String, String> adMap = new HashMap<String, String>();
//                                if (ad.has("isArticle") && ad.optString("isArticle").equals("1")) {
////                                    HashMap<String, String> adMap = new HashMap<String, String>();
//                                    Iterator adIterator = ad.keys();
//                                    while (adIterator.hasNext()) {
//                                        String key = adIterator.next().toString();
//                                        String value = ad.get(key).toString();
////                                        if ("picBig".equals(key)) {
////                                            value += ".2";
////                                        }
//                                        adMap.put(key, value);
//                                    }
//                                    // contentType用于处理广告点击事件做不同的跳转
//                                    adMap.put("contentUrl", ad.optString("contentUrl"));
//                                    adMap.put("contentType", ad.optString("articleType"));
//                                } else {
//                                    // contentType用于处理广告点击事件做不同的跳转
//                                    adMap.put("contentUrl", ad.optString("advurl"));
//                                    adMap.put("contentType", "8");
//                                }
//                                adMap.put("title", ad.optString("advtitle"));
//                                adMap.put("picBig", ad.optString("type2ImgUrl1"));
//                                adMap.put("picNewBig", ad.optString("type2ImgUrl1"));
//                                adMap.put("picMiddle", ad.optString("type2ImgUrl1"));
//                                adMap.put("picSmall", ad.optString("type2ImgUrl1"));
//                                adMap.put("articleType", "8");
//                                adMap.put("label", ad.optString("label"));
//                                columnList.add(index, adMap);
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.getStackTrace();
//            e.getMessage();
//            return columnList;
//        }
//        return columnList;
//    }
//
//    /**
//     * 返回栏目广告数目
//     *
//     * @param map
//     * @return
//     */
//    public static int getColumnAdCount(HashMap<String, Object> map) {
//        int adCount = 0;
//        try {
//            JSONArray advJsonList;
//            if (map != null && map.containsKey("adv")) {
//                advJsonList = (JSONArray) map.get("adv");
//                adCount = advJsonList.length();
//            }
//        } catch (Exception e) {
//            e.getStackTrace();
//            e.getMessage();
//            return adCount;
//        }
//        return adCount;
//    }
//
//    /**
//     * 根据站点和栏目类型获取版本号
//     *
//     * @param siteId 父栏目id
//     * @return
//     */
//    public static long getColumnVersionByType(Context context, int siteId, int type) {
//        NewsColumnAttHelper newsColumnAttObj = new NewsColumnAttHelper(context);
//        long version = 0;
//        newsColumnAttObj.open();
//        NewsColumnAttBean bean = newsColumnAttObj.select(siteId, type);
//        newsColumnAttObj.close();
//        if (bean != null) {
//            version = bean.getNewsColumnAttVersion();
//        }
//        return version;
//    }
//
//    /**
//     * 解析栏目稿件的当前数量(2.0)
//     *
//     * @param map
//     * @return
//     */
//    public static int getColumnArticlesCount(HashMap map) {
//        int counter = 0;
//        JSONArray docsArray;
//        if (map != null && map.containsKey("articles")) {
//            try {
//                if (map.get("articles") instanceof JSONArray) {
//                    docsArray = (JSONArray) map.get("articles");
//                } else {
//                    docsArray = new JSONArray((String) map.get("articles"));
//                }
//                if (docsArray != null) {
//                    counter = docsArray.length();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                return counter;
//            }
//        }
//        return counter;
//    }
//
//    public static long getServiceColumnVersion(int siteId, int parentColumnId) {
//        long attIdVersion = 0;
//        String fileName = getColumnFileName(siteId, parentColumnId);
//        String strColumns = NFCacheManager.getInstance().getFileCache().readCache(fileName);
//        if (!TextUtils.isEmpty(strColumns)) {
//            try {
//                JSONObject jsonObj = new JSONObject(strColumns);
//                attIdVersion = Long.parseLong(jsonObj.get("version")
//                        .toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//                return attIdVersion;
//            }
//        }
//        return attIdVersion;
//    }
//
//    /**
//     * 返回首页我的订阅数据
//     *
//     * @param map
//     * @return
//     */
//    public static List<ArticleBean> getMySubscription(HashMap map) {
//        if (map != null && map.size() > 0 && map.containsKey("mySubNfhArticles")) {
//            try {
//                JSONArray array = (JSONArray) map.get("mySubNfhArticles");
//                if (array != null) {
//                    String subscription = array.toString();
//                    List<ArticleBean> list = new ArrayList<>();
//                    if (!TextUtils.isEmpty(subscription)) {
//                        list = new Gson().fromJson(subscription, new TypeToken<List<ArticleBean>>() {
//                        }.getType());
//                    }
//                    return list;
//                }
//            } catch (JsonSyntaxException e) {
//                e.printStackTrace();
//                return null;
//            }
//
//        }
//        return null;
//    }
//
//    /**
//     * 获取首页猜你喜欢
//     *
//     * @param map
//     * @return
//     */
//    public static ArrayList<SubscriptionChildColumn> getLikeNFHColumns(HashMap map) {
//        if (map == null || map.size() == 0) {
//            return null;
//        } else if (map != null && map.containsKey("nfhHomeRec")) {
//            try {
//                JSONArray nfhArray = (JSONArray) map.get("nfhHomeRec");
//                if (nfhArray != null && nfhArray.length() > 2) {
//                    int size = nfhArray.length();
//                    ArrayList<SubscriptionChildColumn> columns = new ArrayList<>();
//                    for (int i = 0; i < size; i++) {
//                        JSONObject jsonObject = (JSONObject) nfhArray.get(i);
//                        SubscriptionChildColumn column = new SubscriptionChildColumn();
//                        column.setColumnId(jsonObject.optString("r_subId"));
//                        column.setColumnName(jsonObject.optString("r_subName"));
//                        column.setPhoneIcon(jsonObject.optString("r_iconSmall"));
//                        column.setPadIcon(jsonObject.optString("r_iconBig"));
//                        column.setRecommend(jsonObject.optString("r_recommend"));
//                        column.setDescription(jsonObject.optString("r_description"));
//                        if (Account.checkAccountInfo(ReaderApplication.getInstace()) == null) {
//                            column.setUserId(AppUtil.getMyUUID());
//                        } else {
//                            column.setUserId(Account.checkAccountInfo(ReaderApplication.getInstace()).getUserId());
//                        }
//                        column.setIsSubscription(SubcriptionUtil.getSubcriptionChildColumnStatus(column));
//                        columns.add(column);
//                    }
//                    return columns;
//                } else {
//                    return null;
//                }
//            } catch (Exception e) {
//                LogUtil.e("解析猜你喜欢错误：", e.getLocalizedMessage());
//            }
//        }
//        return null;
//    }

}
