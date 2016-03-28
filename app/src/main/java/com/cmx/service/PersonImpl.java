package com.cmx.service;

import android.os.RemoteException;

/**
 * Created by Johnson Lu on 16-3-28.
 */

/**
 * PersonImpl类继承了IPerson.Stub，而Stub继承了Binder，Binder又实现了IBinder。
 * 所以，PersonImpl可以理解为一个IBinder。
 */
public class PersonImpl extends IPerson.Stub {
    private String name;
    private String sex;
    private int age;

    @Override
    public void setName(String name) throws RemoteException {
        // TODO Auto-generated method stub
        this.name = name;

    }

    @Override
    public void setSex(String sex) throws RemoteException {
        // TODO Auto-generated method stub
        this.sex = sex;

    }
    @Override
    public void setAge(int age) throws RemoteException {
        // TODO Auto-generated method stub
        this.age = age;

    }

    @Override
    public String getPerson() throws RemoteException {
        // TODO Auto-generated method stub
        return "name = "+name+", sex = "+sex+", age = "+age;
    }
}
