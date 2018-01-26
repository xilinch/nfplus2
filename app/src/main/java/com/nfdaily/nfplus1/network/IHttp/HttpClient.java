package com.nfdaily.nfplus1.network.IHttp;


import com.nfdaily.nfplus1.network.ReqInfo;

/**
 * @author fengjingyu@foxmail.com
 */
public interface HttpClient {

    void http(ReqInfo reqInfo, RespHandler resphandler, Interceptor interceptor);

    void http(ReqInfo reqInfo, RespHandler resphandler);

    void http(ReqInfo reqInfo);

    //todo
    //void https(ReqInfo reqInfo, RespHandler resphandler, Interceptor interceptor);

}
