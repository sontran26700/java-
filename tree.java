/**
 * DO NOT ADD PACKAGE TO THIS FILE
 * @author [Your-Student-ID]
 */
import java.util.*;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Formatter;
public class Test {

	public static void main(String[] args) 
	{
		

		ArrayList<String> s = new ArrayList<String>();
		ArrayList<SinhVien> hsg = new ArrayList<SinhVien>();
		ArrayList<GiangVien> gvg = new ArrayList<GiangVien>();
		ArrayList<MonHoc> mhg = new ArrayList<MonHoc>();
		String filePath="input.dat";
		Scanner sc =null;
		File file =new File(filePath);
		try
		{
			sc = new Scanner(file);
			while(sc.hasNext())
			{	
				String str =sc.nextLine();
				
   				//System.out.println(str) ;
				Pattern pt =Pattern.compile("#(\\S++)");
				Matcher mc = pt.matcher(str);
				//System.out.println(pt.matcher(str));
				while(mc.find()){

					s.add(mc.group(1));
					//System.out.print();

					if(mc.group(1).equals("SinhVien"))
					{

						String temp2[] = str.split("\\t");
						String temp3[] =temp2[1].split(",");
						String id    =temp3[0];
						String name  =temp3[1];
						char   gender=temp3[2].charAt(0);
						SinhVien hs = new SinhVien(id,name,gender);
						hsg.add(hs);

					}
					if (mc.group(1).equals("GiangVien")){
						String temp4[]=str.split("\t");
						String temp5[]=temp4[1].split(",");
						String id =temp5[0];
						String name=temp5[1];
						char  gender=temp5[2].charAt(0);
						GiangVien gv= new GiangVien(id,name,gender);
						gvg.add(gv);
						//System.out.println(gender);	
					}
					if (mc.group(1).equals("MonHoc")){
						String temp6[]=str.split("\t");
						String temp7[]=temp6[1].split(",");
						String mamh =temp7[0];
						String name=temp7[1];
						int tinchi = Integer.parseInt(temp7[2]);
						MonHoc Monhoc =null;
						mhg.add(new MonHoc(temp7[0],temp7[1],Integer.parseInt(temp7[2])));

						for(MonHoc mh : mhg){
							if(mh.getMaMH().equals(temp7[0])){
								Monhoc= mh;
								break;	
							}
							//System.out.println(temp7[0]);
						}
						
						for(int i=0;i < hsg.size();i++){
							for(int j=0;j<hsg.get(i).getSvTKB().size();j++){
								if(hsg.get(i).getSvTKB().get(j).getMaMH().equals(mamh)){
									hsg.get(i).getSvTKB().get(j).setTenMH(name);
									hsg.get(i).getSvTKB().get(j).setTinChi(tinchi);
									break;
								}
							}
						}
						for(int i=0;i < gvg.size();i++){
							for(int j=0;j<gvg.get(i).getGvTKB().size();j++){
								if(gvg.get(i).getGvTKB().get(j).getMaMH().equals(mamh)){
									gvg.get(i).getGvTKB().get(j).setTenMH(name);
									gvg.get(i).getGvTKB().get(j).setTinChi(tinchi);
									break;
								}
							}
						}
						
						
						for(SinhVien sv : hsg){
							for(MonHoc mh: sv.getSvTKB()){
								//System.out.println(sv.getSvTKB());
								if(temp7[0].equals(mh.getMaMH())){
									mh.setTenMH(temp7[1]);
									//mh.setTinChi(tinchi);	
									sv.setSoTinChiTichLuy(sv.getSoTinChiTichLuy()+Integer.parseInt(temp7[2]));
									System.out.println(sv.getSoTinChiTichLuy());

								}
							}
						}
						for(GiangVien gv :gvg){
							for(MonHoc mh2:gv.getGvTKB()){
								if(temp7[0].equals(mh2.getMaMH())){
									int lop = 0;
									lop++;
									gv.setSoLopGiangDay(gv.getSoLopGiangDay()+lop);
								//System.out.println(gv.getSoLopGiangDay());
								}	
							}
						}
					}
					
					if (mc.group(1).equals("svTKB")){
						String temp8[]=str.split("\t");
						String temp9[]=temp8[1].split(",");
						String idhs =temp9[0];
						String maMH=temp9[1];
						MonHoc Monhoc = new MonHoc(); 
						for(SinhVien sv : hsg){
							if(sv.getId().equals(temp9[0])){
								
								Monhoc.setMaMH(temp9[1]);
								sv.addMonHoc(Monhoc);	
							//System.out.println(monhoc.getMaMH());
							}
							//System.out.println(sv);	
						}						            		 
					}
					if (mc.group(1).equals("gvTKB")){
						String temp10[]=str.split("\t");
						String temp11[]=temp10[1].split(",");
						String idhs =temp11[0];
						String maMH=temp11[1];
						MonHoc Monhoc = new MonHoc();
						for(GiangVien gvs:gvg){
							if(gvs.getId().equals(idhs)){
								
								Monhoc.setMaMH(maMH);
								gvs.addMonHoc(Monhoc);
            				//System.out.println(monhoc.getMaMH());
								break;
							}
							
						}
					}
					
				}
				
			} 
			//System.out.println(gvg.get(2).getId()+gvg.get(2).getName()+gvg.get(2).getGender()+gvg.get(2).getSoLopGiangDay());
		}
		
		catch(IOException e){
			e.printStackTrace();
		}
//==================================================================================================		
		Formatter output01 = new Formatter(); //DONE
		try
		{
			output01 = new Formatter("4e.dat");
			sc = new Scanner(file);
			
			String str =sc.next();
			
   				//System.out.println(str) ;
			Pattern pt =Pattern.compile("#(\\S++)");
			Matcher mc = pt.matcher(str);
			
			String[] split = str.split(",");
			String mamH= split[1];
			MonHoc monhoc = new MonHoc();
			
			for(MonHoc mh : mhg){
				if(mh.getMaMH().equals(mamH)){
					monhoc = mh;
						//System.out.println(mh);
				}
			}
			for(SinhVien hs : hsg){
				for(MonHoc mh:hs.getSvTKB()){
					if(mh.getMaMH().equals((monhoc).getMaMH())){
						output01.format(hs.getId()+","+hs.getName()+","+hs.getGender()+"\n");
					}
				}
			}
			output01.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
//================================================================================================			
		Formatter output02 = new Formatter(); //DONE
		try
		{
			output02 = new Formatter("4f.dat");
			sc = new Scanner(file);
			
			String str =sc.next();
			
   				//System.out.println(str) ;
			Pattern pt =Pattern.compile("#(\\S++)");
			Matcher mc = pt.matcher(str);
			
			String[] split = str.split(",");
			String maGV= split[2];

			GiangVien giangvien = new GiangVien();
			for(GiangVien gv:gvg){
				if(gv.getId().equals(maGV)){
					giangvien = gv ;
					//System.out.println(giangvien);
				}
			}
			
			for(MonHoc mh : giangvien.getGvTKB()){
					//System.out.print(giangvien.getGvTKB());
				
						//System.out.print(giangvien.getGvTKB());
				output02.format(mh.getMaMH()+","+mh.getTenMH()+","+mh.getTinChi());
				
			}
			
			output02.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
//=========================================================================================			
		Formatter output00 = new Formatter(); //cần setTinChi
		try
		{
			output00 = new Formatter("4d.dat");
			sc = new Scanner(file);
			
			String str =sc.next();
				//ArrayList xx = new ArrayList();
   				//System.out.println(str) ;
			Pattern pt =Pattern.compile("#(\\S++)");
			Matcher mc = pt.matcher(str);
			
			String[] split = str.split(",");
			String maSV= split[0];
			SinhVien sinhvien = new SinhVien();
			
			for(SinhVien svs:hsg){
				if(svs.getId().equals(maSV)){
					sinhvien=svs;
				}
			}
			for(MonHoc mh: sinhvien.getSvTKB()){
				output00.format(mh.getMaMH()+","+mh.getTenMH()+","+mh.getTinChi());
			}
			output00.close();
			

		}
		catch(IOException e){
			e.printStackTrace();
		}
	
//===============================================================================================		
				Formatter output = new Formatter();  //DONE
				try{
					output = new Formatter("Students.dat");
					for(int i=0;i < hsg.size();i++){
						output.format(hsg.get(i).getId()+","+hsg.get(i).getName()+","+hsg.get(i).getGender()+","+hsg.get(i).getSoTinChiTichLuy()+"\n");
						
					}
					output.close();

				}

				catch(IOException e){
					e.printStackTrace();
				}
				finally{
					output.close();
				}
				
//=======================================================================================		
				Formatter output06 = new Formatter();
				try{
					
					output06 = new Formatter("Teachers.dat");
					for(int j=0;j < gvg.size();j++){
						output06.format(gvg.get(j).getId()+","+gvg.get(j).getName()+","+gvg.get(j).getGender()+","+gvg.get(j).getSoLopGiangDay()+"\n");
						
					}
					output06.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
				finally{
					output06.close();
				}
		
//=========================================================================================
				
				
				
				
				
				
				
				
				
				
				

				
			}
		}