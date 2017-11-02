package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class A1 {
	private Connection conn;
	private String Driver="jdbc:mysql://222.30.192.120/moqui-yzeb?useUnicode=true&characterEncoding=gbk";
	private String username="jrw";
	private String pwd="rootroot";
	private Statement st,st2,st3;
	private ResultSet rs,rs1,rs2;
	private float Ssalemoney,Sprofit;
	int j;
	String sqls;
	public void init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(Driver,username,pwd);			
			String sql="Select GOODS_NAME from sales_statistics_by_period where  START_TIME between '2016-10-01 00:00:00' and '2017-04-01 00:00:00'";

			 sqls="Select sum(SALE_MONEY),sum(PROFIT) from sales_statistics_by_period where  START_TIME between '2016-10-01 00:00:00' and '2017-4-01 00:00:00' ";
			st=conn.createStatement();
			
			rs=st.executeQuery(sql);
			
			List list=new ArrayList();
						
			while(rs.next()){
				list.add(rs.getString(1));

			}
			
			 for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {       
			      for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {       
			           if  (list.get(j).equals(list.get(i)))  {       
			              list.remove(j);       
			            }        
			        }        
			      }    
			 rs2=st.executeQuery(sqls);
				while(rs2.next()){
					Ssalemoney=rs2.getFloat(1);
					Sprofit=rs2.getFloat(2);
				}
			 System.out.println("商品名称,销售额 ,利润,销售占比,利润占比");
			 Iterator it=list.iterator();
			
			 while(it.hasNext()){				 
					String sql1="Select sum(SALE_MONEY),sum(PROFIT),GOODS_NAME from sales_statistics_by_period where  GOODS_NAME = '"+it.next()+"'"+" and START_TIME between '2016-10-01 00:00:00' and '2017-04-01 00:00:00'";
					rs1=st.executeQuery(sql1);
					j++;
					while(rs1.next()){						
						 System.out.println(rs1.getString(3)+","+rs1.getFloat(1)+","+rs1.getFloat(2)+","+rs1.getFloat(1)/Ssalemoney*100+"%,"+rs1.getInt(2)/Sprofit*100+"%");
					}
					
			 }						
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] arg){
		new A1().init();
	}
}
