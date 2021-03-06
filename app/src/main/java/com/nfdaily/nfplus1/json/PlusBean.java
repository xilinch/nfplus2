package com.nfdaily.nfplus1.json;



import com.nfdaily.nfplus1.util.UtilIo;

import java.io.Serializable;

/**
 * @author  fengjingyu@foxmail.com
 *  1 提供了序列化id 2 浅克隆的方法 3 深克隆的方法
 */
public abstract class PlusBean implements Serializable, Cloneable {

    protected static final long serialVersionUID = 2161633826093329317L;

    protected final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * 浅克隆
     */
    public Object simpleClone() {
        return clone();
    }

    /**
     * 深克隆
     */
    public Object deepClone() {
        return UtilIo.deepClone(this);
    }
}
