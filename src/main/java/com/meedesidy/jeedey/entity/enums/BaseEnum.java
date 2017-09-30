package com.meedesidy.jeedey.entity.enums;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
 
public class BaseEnum {
	public int id = -1;
    public String code;
    public String name;
 
    public BaseEnum() {
    }
 
    public BaseEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
 
    public BaseEnum(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
 
    public static BaseEnum getEnumByInd(Class clazz, int id) {
        return getBaseEnum(clazz, id, null, null);
 
    }
 
    public static BaseEnum getEnumByCode(Class clazz, String code) {
        return getBaseEnum(clazz, -1, code, null);
 
    }
 
    public static BaseEnum getEnumByName(Class clazz, String name) {
        return getBaseEnum(clazz, -1, null, name);
    }
 
    public List<BaseEnum> getList() {
        return getList(getClass(), -1, null, null);
    }
 
    private static BaseEnum getBaseEnum(Class clazz, int id, String code,
            String name) {
        try {
            List<BaseEnum> enums = getList(clazz.newInstance(), id, code, name);
            if (enums.size() > 0) {
                return (BaseEnum) enums.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    private static List<BaseEnum> getList(Object obj, int id, String code,
            String name) {
        Field[] fields = obj.getClass().getFields();
        List<BaseEnum> retList = new ArrayList<BaseEnum>();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            if (f.getType().equals(obj.getClass())) {
                try {
                    BaseEnum be = (BaseEnum) f.get(obj);
                    if (id == -1 && StringUtils.isBlank(code)
                            && StringUtils.isBlank(name)) {
                        retList.add(be);
                    } else if (id != -1 && id == be.getId()) {
                        retList.add(be);
                        return retList;
                    } else if (StringUtils.isNotBlank(code)
                            && code.equals(be.getCode())) {
                        retList.add(be);
                        return retList;
                    } else if (StringUtils.isNotBlank(name)
                            && name.equals(be.getName())) {
                        retList.add(be);
                        return retList;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return retList;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
        repair();
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
        repair();
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
        repair();
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof BaseEnum) {
            BaseEnum em = (BaseEnum) obj;
            em.repair();
            return em.getId() == getId();
        }
        return super.equals(obj);
    }
 
    private void repair() {
        BaseEnum em = getBaseEnum(getClass(), id, code, name);
        if (id == -1 && em.getId() != -1) {
            setId(em.getId());
        }
        if (StringUtils.isBlank(code) && StringUtils.isNotBlank(em.getCode())) {
            setCode(em.getCode());
        }
        if (StringUtils.isBlank(name) && StringUtils.isNotBlank(em.getName())) {
            setName(em.getName());
        }
    }
}
