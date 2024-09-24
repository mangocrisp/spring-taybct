package io.github.mangocrisp.spring.taybct.common.dict;


/**
 * @author XiJieYin <br> 2023/5/19 17:30
 */
public abstract class AbstractSysDict implements ISysDict {
    private static final long serialVersionUID = -6712591123942780370L;
    private final String key;
    private final String val;

    protected AbstractSysDict(String key, String val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getVal() {
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ISysDict)) {
            return false;
        }
        ISysDict that = (ISysDict) obj;
        return getKey().equals(that.getKey());
    }

    @Override
    public int hashCode() {
        return getKey().hashCode();
    }
}
