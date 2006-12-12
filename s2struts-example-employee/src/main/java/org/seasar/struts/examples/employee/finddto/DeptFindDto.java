package org.seasar.struts.examples.employee.finddto;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.seasar.buri.common.util.ScriptProcessor;

public class DeptFindDto {
	public static final String TABLE = "dept";
    private ArrayList orderList = new ArrayList();
	
	private Integer deptno = null;
	private Integer deptno_not = null;
	private Integer deptno_large = null;
	private Integer deptno_moreLarge = null;
	private Integer deptno_from = null;
	private Integer deptno_to = null;
	private Integer deptno_moreSmall = null;
	private Integer deptno_small = null;
	private List deptno_in = null;
	private Boolean deptno_isNull = null;
	private Boolean deptno_isNotNull = null;
	private boolean deptno_isASC = true;
	private String dname = null;
	private String dname_not = null;
	private String dname_large = null;
	private String dname_moreLarge = null;
	private String dname_from = null;
	private String dname_to = null;
	private String dname_moreSmall = null;
	private String dname_small = null;
	private String dname_matchFull = null;
	private String dname_matchFront = null;
	private String dname_matchBack = null;
	private List dname_in = null;
	private Boolean dname_isNull = null;
	private Boolean dname_isNotNull = null;
	private boolean dname_isASC = true;
	private String loc = null;
	private String loc_not = null;
	private String loc_large = null;
	private String loc_moreLarge = null;
	private String loc_from = null;
	private String loc_to = null;
	private String loc_moreSmall = null;
	private String loc_small = null;
	private String loc_matchFull = null;
	private String loc_matchFront = null;
	private String loc_matchBack = null;
	private List loc_in = null;
	private Boolean loc_isNull = null;
	private Boolean loc_isNotNull = null;
	private boolean loc_isASC = true;
	private Integer versionno = null;
	private Integer versionno_not = null;
	private Integer versionno_large = null;
	private Integer versionno_moreLarge = null;
	private Integer versionno_from = null;
	private Integer versionno_to = null;
	private Integer versionno_moreSmall = null;
	private Integer versionno_small = null;
	private List versionno_in = null;
	private Boolean versionno_isNull = null;
	private Boolean versionno_isNotNull = null;
	private boolean versionno_isASC = true;

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public Integer getDeptno_not() {
		return deptno_not;
	}

	public void setDeptno_not(Integer deptno_not) {
		this.deptno_not = deptno_not;
	}
	public Integer getDeptno_large() {
		return deptno_large;
	}

	public void setDeptno_large(Integer deptno_large) {
		this.deptno_large = deptno_large;
	}
	public Integer getDeptno_moreLarge() {
		return deptno_moreLarge;
	}

	public void setDeptno_moreLarge(Integer deptno_moreLarge) {
		this.deptno_moreLarge = deptno_moreLarge;
	}
	public Integer getDeptno_from() {
		return deptno_from;
	}

	public void setDeptno_from(Integer deptno_from) {
		this.deptno_from = deptno_from;
	}
	public Integer getDeptno_to() {
		return deptno_to;
	}

	public void setDeptno_to(Integer deptno_to) {
		this.deptno_to = deptno_to;
	}
	public Integer getDeptno_moreSmall() {
		return deptno_moreSmall;
	}

	public void setDeptno_moreSmall(Integer deptno_moreSmall) {
		this.deptno_moreSmall = deptno_moreSmall;
	}
	public Integer getDeptno_small() {
		return deptno_small;
	}

	public void setDeptno_small(Integer deptno_small) {
		this.deptno_small = deptno_small;
	}
	public List getDeptno_in() {
		return deptno_in;
	}

	public void setDeptno_in(List deptno_in) {
		this.deptno_in = deptno_in;
	}
	public Boolean getDeptno_isNull() {
		return deptno_isNull;
	}

	public void setDeptno_isNull(Boolean deptno_isNull) {
		this.deptno_isNull = deptno_isNull;
	}
	public Boolean getDeptno_isNotNull() {
		return deptno_isNotNull;
	}

	public void setDeptno_isNotNull(Boolean deptno_isNotNull) {
		this.deptno_isNotNull = deptno_isNotNull;
	}
	public boolean getDeptno_isASC() {
		return deptno_isASC;
	}

	public void setDeptno_isASC(boolean deptno_isASC) {
		this.deptno_isASC = deptno_isASC;
	}
	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDname_not() {
		return dname_not;
	}

	public void setDname_not(String dname_not) {
		this.dname_not = dname_not;
	}
	public String getDname_large() {
		return dname_large;
	}

	public void setDname_large(String dname_large) {
		this.dname_large = dname_large;
	}
	public String getDname_moreLarge() {
		return dname_moreLarge;
	}

	public void setDname_moreLarge(String dname_moreLarge) {
		this.dname_moreLarge = dname_moreLarge;
	}
	public String getDname_from() {
		return dname_from;
	}

	public void setDname_from(String dname_from) {
		this.dname_from = dname_from;
	}
	public String getDname_to() {
		return dname_to;
	}

	public void setDname_to(String dname_to) {
		this.dname_to = dname_to;
	}
	public String getDname_moreSmall() {
		return dname_moreSmall;
	}

	public void setDname_moreSmall(String dname_moreSmall) {
		this.dname_moreSmall = dname_moreSmall;
	}
	public String getDname_small() {
		return dname_small;
	}

	public void setDname_small(String dname_small) {
		this.dname_small = dname_small;
	}
	public String getDname_matchFull() {
		if(dname_matchFull==null) {
			return null;
		}
		return "%"+dname_matchFull+"%";
	}

	public void setDname_matchFull(String dname_matchFull) {
		this.dname_matchFull = dname_matchFull;
	}
	public String getDname_matchFront() {
		if(dname_matchFront==null) {
			return null;
		}
		return dname_matchFront+"%";
	}

	public void setDname_matchFront(String dname_matchFront) {
		this.dname_matchFront = dname_matchFront;
	}
	public String getDname_matchBack() {
		if(dname_matchBack==null) {
			return null;
		}
		return "%"+dname_matchBack;
	}

	public void setDname_matchBack(String dname_matchBack) {
		this.dname_matchBack = dname_matchBack;
	}
	public List getDname_in() {
		return dname_in;
	}

	public void setDname_in(List dname_in) {
		this.dname_in = dname_in;
	}
	public Boolean getDname_isNull() {
		return dname_isNull;
	}

	public void setDname_isNull(Boolean dname_isNull) {
		this.dname_isNull = dname_isNull;
	}
	public Boolean getDname_isNotNull() {
		return dname_isNotNull;
	}

	public void setDname_isNotNull(Boolean dname_isNotNull) {
		this.dname_isNotNull = dname_isNotNull;
	}
	public boolean getDname_isASC() {
		return dname_isASC;
	}

	public void setDname_isASC(boolean dname_isASC) {
		this.dname_isASC = dname_isASC;
	}
	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getLoc_not() {
		return loc_not;
	}

	public void setLoc_not(String loc_not) {
		this.loc_not = loc_not;
	}
	public String getLoc_large() {
		return loc_large;
	}

	public void setLoc_large(String loc_large) {
		this.loc_large = loc_large;
	}
	public String getLoc_moreLarge() {
		return loc_moreLarge;
	}

	public void setLoc_moreLarge(String loc_moreLarge) {
		this.loc_moreLarge = loc_moreLarge;
	}
	public String getLoc_from() {
		return loc_from;
	}

	public void setLoc_from(String loc_from) {
		this.loc_from = loc_from;
	}
	public String getLoc_to() {
		return loc_to;
	}

	public void setLoc_to(String loc_to) {
		this.loc_to = loc_to;
	}
	public String getLoc_moreSmall() {
		return loc_moreSmall;
	}

	public void setLoc_moreSmall(String loc_moreSmall) {
		this.loc_moreSmall = loc_moreSmall;
	}
	public String getLoc_small() {
		return loc_small;
	}

	public void setLoc_small(String loc_small) {
		this.loc_small = loc_small;
	}
	public String getLoc_matchFull() {
		if(loc_matchFull==null) {
			return null;
		}
		return "%"+loc_matchFull+"%";
	}

	public void setLoc_matchFull(String loc_matchFull) {
		this.loc_matchFull = loc_matchFull;
	}
	public String getLoc_matchFront() {
		if(loc_matchFront==null) {
			return null;
		}
		return loc_matchFront+"%";
	}

	public void setLoc_matchFront(String loc_matchFront) {
		this.loc_matchFront = loc_matchFront;
	}
	public String getLoc_matchBack() {
		if(loc_matchBack==null) {
			return null;
		}
		return "%"+loc_matchBack;
	}

	public void setLoc_matchBack(String loc_matchBack) {
		this.loc_matchBack = loc_matchBack;
	}
	public List getLoc_in() {
		return loc_in;
	}

	public void setLoc_in(List loc_in) {
		this.loc_in = loc_in;
	}
	public Boolean getLoc_isNull() {
		return loc_isNull;
	}

	public void setLoc_isNull(Boolean loc_isNull) {
		this.loc_isNull = loc_isNull;
	}
	public Boolean getLoc_isNotNull() {
		return loc_isNotNull;
	}

	public void setLoc_isNotNull(Boolean loc_isNotNull) {
		this.loc_isNotNull = loc_isNotNull;
	}
	public boolean getLoc_isASC() {
		return loc_isASC;
	}

	public void setLoc_isASC(boolean loc_isASC) {
		this.loc_isASC = loc_isASC;
	}
	public Integer getVersionno() {
		return versionno;
	}

	public void setVersionno(Integer versionno) {
		this.versionno = versionno;
	}
	public Integer getVersionno_not() {
		return versionno_not;
	}

	public void setVersionno_not(Integer versionno_not) {
		this.versionno_not = versionno_not;
	}
	public Integer getVersionno_large() {
		return versionno_large;
	}

	public void setVersionno_large(Integer versionno_large) {
		this.versionno_large = versionno_large;
	}
	public Integer getVersionno_moreLarge() {
		return versionno_moreLarge;
	}

	public void setVersionno_moreLarge(Integer versionno_moreLarge) {
		this.versionno_moreLarge = versionno_moreLarge;
	}
	public Integer getVersionno_from() {
		return versionno_from;
	}

	public void setVersionno_from(Integer versionno_from) {
		this.versionno_from = versionno_from;
	}
	public Integer getVersionno_to() {
		return versionno_to;
	}

	public void setVersionno_to(Integer versionno_to) {
		this.versionno_to = versionno_to;
	}
	public Integer getVersionno_moreSmall() {
		return versionno_moreSmall;
	}

	public void setVersionno_moreSmall(Integer versionno_moreSmall) {
		this.versionno_moreSmall = versionno_moreSmall;
	}
	public Integer getVersionno_small() {
		return versionno_small;
	}

	public void setVersionno_small(Integer versionno_small) {
		this.versionno_small = versionno_small;
	}
	public List getVersionno_in() {
		return versionno_in;
	}

	public void setVersionno_in(List versionno_in) {
		this.versionno_in = versionno_in;
	}
	public Boolean getVersionno_isNull() {
		return versionno_isNull;
	}

	public void setVersionno_isNull(Boolean versionno_isNull) {
		this.versionno_isNull = versionno_isNull;
	}
	public Boolean getVersionno_isNotNull() {
		return versionno_isNotNull;
	}

	public void setVersionno_isNotNull(Boolean versionno_isNotNull) {
		this.versionno_isNotNull = versionno_isNotNull;
	}
	public boolean getVersionno_isASC() {
		return versionno_isASC;
	}

	public void setVersionno_isASC(boolean versionno_isASC) {
		this.versionno_isASC = versionno_isASC;
	}


    public void addOrderList(String order) {
        orderList.add(order);
    }

    public void addOrderList(String order,boolean isAsc) {
        orderList.add(order);
        ScriptProcessor processor = new ScriptProcessor();
        processor.setValue(order.replace('.','_') + "_isASC",this,new Boolean(isAsc));
    }
    
    public String getOrderList() {
        String order = "";
        String ORDER = "ORDER BY ";
        Iterator ite = orderList.iterator();
        ScriptProcessor processor = new ScriptProcessor();
        while(ite.hasNext()) {
            String orderTgt = (String)ite.next();
            order = ORDER + order + orderTgt.replace('_','.') + " ";
            Boolean var = (Boolean)processor.getValue(orderTgt + "_isASC", this);
            if( ! var.booleanValue()) {
                order = order + "DESC ";
            }
            ORDER = "";
        }
        return order;
    }

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/deptno=").append(deptno);
		buff.append("/deptno_not=").append(deptno_not);
		buff.append("/deptno_large=").append(deptno_large);
		buff.append("/deptno_moreLarge=").append(deptno_moreLarge);
		buff.append("/deptno_from=").append(deptno_from);
		buff.append("/deptno_to=").append(deptno_to);
		buff.append("/deptno_moreSmall=").append(deptno_moreSmall);
		buff.append("/deptno_small=").append(deptno_small);
		buff.append("/deptno_in=").append(deptno_in);
		buff.append("/deptno_isNull=").append(deptno_isNull);
		buff.append("/deptno_isNotNull=").append(deptno_isNotNull);
		buff.append("/deptno_isASC=").append(deptno_isASC);
		buff.append("/dname=").append(dname);
		buff.append("/dname_not=").append(dname_not);
		buff.append("/dname_large=").append(dname_large);
		buff.append("/dname_moreLarge=").append(dname_moreLarge);
		buff.append("/dname_from=").append(dname_from);
		buff.append("/dname_to=").append(dname_to);
		buff.append("/dname_moreSmall=").append(dname_moreSmall);
		buff.append("/dname_small=").append(dname_small);
		buff.append("/dname_in=").append(dname_in);
		buff.append("/dname_isNull=").append(dname_isNull);
		buff.append("/dname_isNotNull=").append(dname_isNotNull);
		buff.append("/dname_isASC=").append(dname_isASC);
		buff.append("/loc=").append(loc);
		buff.append("/loc_not=").append(loc_not);
		buff.append("/loc_large=").append(loc_large);
		buff.append("/loc_moreLarge=").append(loc_moreLarge);
		buff.append("/loc_from=").append(loc_from);
		buff.append("/loc_to=").append(loc_to);
		buff.append("/loc_moreSmall=").append(loc_moreSmall);
		buff.append("/loc_small=").append(loc_small);
		buff.append("/loc_in=").append(loc_in);
		buff.append("/loc_isNull=").append(loc_isNull);
		buff.append("/loc_isNotNull=").append(loc_isNotNull);
		buff.append("/loc_isASC=").append(loc_isASC);
		buff.append("/versionno=").append(versionno);
		buff.append("/versionno_not=").append(versionno_not);
		buff.append("/versionno_large=").append(versionno_large);
		buff.append("/versionno_moreLarge=").append(versionno_moreLarge);
		buff.append("/versionno_from=").append(versionno_from);
		buff.append("/versionno_to=").append(versionno_to);
		buff.append("/versionno_moreSmall=").append(versionno_moreSmall);
		buff.append("/versionno_small=").append(versionno_small);
		buff.append("/versionno_in=").append(versionno_in);
		buff.append("/versionno_isNull=").append(versionno_isNull);
		buff.append("/versionno_isNotNull=").append(versionno_isNotNull);
		buff.append("/versionno_isASC=").append(versionno_isASC);
		buff.append("]");
		return buff.toString();
	}
	
}
