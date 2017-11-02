package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Conn {
	private Connection conn;
	private String Driver="jdbc:mysql://222.30.192.120/moqui-yzeb?useUnicode=true&characterEncoding=gbk";
	private String username="jrw";
	private String pwd="rootroot";
	private Statement st,st1,st2;
	private ResultSet rs,rs1,rs2;
	
	public void init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(Driver,username,pwd);			
			//String sql="Select sum(SALE_AMOUNT),UNIT_PRICE,COST,sum(SALE_MONEY),sum(PROFIT),GOODS_NAME from sales_statistics_by_period where  START_TIME between '2016-10-01 00:00:00' and '2017-4-01 00:00:00' and GOODS_NAME = 'test'";
			String sql1="";
			String sql2="";
			String sql3="";
			int i=0;
			int j=0;
			System.out.println("日期,上午7:30-12:30,中午12:31-18:30,晚上18:31-23:00");
			//String a="00";
			for(j=1;j<8;j++){
			for(i=1;i<=31;i++){
				
				if(i<10){
					 sql1="select sum(SALE_AMOUNT),sum(SALE_MONEY),sum(PROFIT) from sales_statistics_by_period where START_TIME between '2017-0"+j+"-0"+i+" 07:30:00' and '2017-0"+j+"-0"+i+" 12:30:00'";
					 sql2="select sum(SALE_AMOUNT),sum(SALE_MONEY),sum(PROFIT) from sales_statistics_by_period where START_TIME between '2017-0"+j+"-0"+i+" 12:31:00' and '2017-0"+j+"-0"+i+" 18:31:00'";
					 sql3="select sum(SALE_AMOUNT),sum(SALE_MONEY),sum(PROFIT) from sales_statistics_by_period where START_TIME between '2017-0"+j+"-0"+i+" 18:31:00' and '2017-0"+j+"-0"+i+" 23:00:00'";

				}else{
					 sql1="select sum(SALE_AMOUNT),sum(SALE_MONEY),sum(PROFIT) from sales_statistics_by_period where START_TIME between '2017-0"+j+"-"+i+" 07:30:00' and '2017-0"+j+"-"+i+" 12:30:00'";
					 sql2="select sum(SALE_AMOUNT),sum(SALE_MONEY),sum(PROFIT) from sales_statistics_by_period where START_TIME between '2017-0"+j+"-"+i+" 12:31:00' and '2017-0"+j+"-"+i+" 18:31:00'";
					 sql3="select sum(SALE_AMOUNT),sum(SALE_MONEY),sum(PROFIT) from sales_statistics_by_period where START_TIME between '2017-0"+j+"-"+i+" 18:31:00' and '2017-0"+j+"-"+i+" 23:00:00'";

				}
			//String sql="select table_name from information_schema.tables where table_schema='moqui-yzeb'";
			
			st=conn.createStatement();
			st1=conn.createStatement();
			st2=conn.createStatement();
			rs=st.executeQuery(sql1);
			rs1=st1.executeQuery(sql2);
			rs2=st2.executeQuery(sql3);
			
			System.out.print("2017-"+j+"-"+i+",");
			while(rs.next()){			
				//System.out.print("7:30-12:30");
				//System.out.print(rs.getString(1)+",");
				//System.out.print(rs.getString(2)+",");
				System.out.print(rs.getString(3)+",");//利润
				
			}
			while(rs1.next()){
				
				
				//System.out.print(rs1.getString(1)+",");//"售卖数量:"+
				//System.out.print(rs1.getString(2)+",");//销售额
				System.out.print(rs1.getString(3)+",");
				
			}
			while(rs2.next()){				
				//System.out.print("18:31-23:00");
				//	System.out.println(rs2.getString(1));
				//System.out.println(rs2.getString(2));
				System.out.println(rs2.getString(3));
				
			}
			
			}
		
			}
	/*		
			List list=new ArrayList();
			while(rs.next()){
				list.add(rs.getString(1));
			//if(rs.getString(1)!=a[])			
			//System.out.println(rs.getString(1));		

			}
			 for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {       
			      for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {       
			           if  (list.get(j).equals(list.get(i)))  {       
			              list.remove(j);       
			            }        
			        }        
			      }    
				Iterator it=list.iterator();
			 while(it.hasNext()){
				 System.out.println(it.next());
				
			 }
			// */
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
		new Conn().init();
	}
}
