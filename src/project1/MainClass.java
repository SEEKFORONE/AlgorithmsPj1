package project1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainClass {
	static RBTree t = new RBTree();
	static BTree  bt = new BTree(10);
	public static void main(String[] args){
//		���ȶ����ݽ��г�ʼ��
		
//	 long time1 =  System.nanoTime()/1000L;
	 initial(0);
//	 long time2 =  System.nanoTime()/1000L;
	 
//	 System.out.println("\n��ʼ�������������1_initial.txt���� ������ʱ��Ϊ��"+(time2-time1)+"΢��");
	 System.out.println("�������Ѿ���ʼ����ϣ�");
	 System.out.println("��ѡ����Ҫ���в���������h: �������b: B����");
	 Scanner choTree = new Scanner(System.in);
	 String choStr = choTree.nextLine();
//	 ��ѡ���˺����ʱ
	 if(choStr.equals("h")){
		 System.out.println("���Ѿ�ѡ���˺������������������ָ����в�����\n0 ���롰ex���˳�����!\n1 ���롰addw����ӵ���\n2 ���롰impo���������ļ�"
			 		+ "\n3 ���롰delw��ɾ������\n4 ���롰delf��ɾ��ָ���ļ�������е���\n5 ���롰sers������������ѯ\n6 ����Ҫ��ѯ�ĵ��ʽ��в�ѯ\n\n");
//		 t.printTree();
		 Scanner input = new Scanner(System.in);
		
		 RBNode re = new RBNode() ;
		 while(true){
			
			 String aStr = input.nextLine();
			 try{
				 if(aStr.equals("ex")){
                     System.out.println("���ѳɹ��˳�����!");
					 break;
				 }else if(aStr.equals("impo")){
					 initial(1);
					 System.out.println("�Ѿ��ɹ����ƶ��ļ�3_insert�е����ݲ��룡");
				 }else if(aStr.equals("addw")){
					 System.out.println("����������Ҫ��ӵĵ��ʣ�");
					 Scanner in0 = new Scanner(System.in);
					 String addW = in0.nextLine();
					 
					 System.out.println("����������Ҫ��ӵĵ������⣺");
					 Scanner in1 = new Scanner(System.in);
					 String addMean = in0.nextLine();
					 
					 int addKey = prepareForInsert(addW);
					 t.insert(addKey, addW, addMean);
					 System.out.println("�Ѿ��������Ҫ��ӵĵ���"+addW);
				 }else if(aStr.equals("delw")){
					 System.out.println("����������Ҫɾ���ĵ��ʣ�");
					 Scanner in = new Scanner(System.in);
					 String deleteW = in.nextLine();
					 int delKey = prepareForInsert(deleteW);
					 int res = t.delete(delKey);
					 if(res == 0)
						 System.out.println("�Ѿ�ɾ������Ҫɾ���ĵ���"+deleteW);
					 else {
						 System.out.println("�õ���"+deleteW+"���ں�����У�");
					}
				 }else if(aStr.equals("delf")){
//					 initial(2);
//					 long time1 =  System.nanoTime()/1000L;
					 deleteFile(1);
//					 long time2 =  System.nanoTime()/1000L;
//					 System.out.println("\n�������ɾ���ļ� ������ʱ��Ϊ��"+(time2-time1)+"΢��");
					 deleteFile(1);
					 System.out.println("�Ѿ��ɹ���ָ���ļ�2_delete�ڵ�����ɾ����");
				 }else if(aStr.equals("sers")){
					 System.out.println("��������ʼ���ʣ�");
					 Scanner scope = new Scanner(System.in);
					 String startStr = scope.nextLine();
					 System.out.println("�������ֹ���ʣ�");
					 String endStr = scope.nextLine();
					 
					 RBsearchSome(prepareForInsert(startStr), prepareForInsert(endStr));
				 }else {
					 if(aStr.equalsIgnoreCase("")){
					 }else{
//						 long time1 =  System.nanoTime()/1000L;
						 re = t.search(prepareForInsert(aStr));
//						 long time2 =  System.nanoTime()/1000L;
						 if(re.getValue()!=null) {
							 System.out.println(re.getValue());
//							 System.out.println("\n��ѯ���� ������ʱ��Ϊ��"+(time2-time1)+"΢��");
						 }
						 else
							 System.out.println("��Ǹ���ʿ���û������Ҫ�²�ѯ�Ĵʣ�");
					 }
				}
				 
			 }catch(Exception e){
				 System.out.println("��ȷ������������ȷ!");
			 }
		 }
//		 ��ѡ����B��ʱ��
	 }else if(choStr.equalsIgnoreCase("b")){
		 initial(3);
//		 bt.printTree();
		 System.out.println("���Ѿ�ѡ����B����������������ָ����в�����\n0 ���롰ex���˳�����!\n1 ���롰addw����ӵ���\n2 ���롰impo���������ļ�"
			 		+ "\n3 ���롰delw��ɾ������\n4 ���롰delf��ɾ��ָ���ļ�������е���\n5 ���롰sers������������ѯ\n6  ����Ҫ��ѯ�ĵ��ʽ��в�ѯ\n\n");
		 
		 Scanner Binput = new Scanner(System.in);
		 while(true){
			String BinputStr = Binput.nextLine();
			try{
			 if(BinputStr.equals("ex")){
				 System.out.println("���ѳɹ��˳�����!");
				 break;
			 }else if(BinputStr.equalsIgnoreCase("addw")){
				 System.out.println("����������Ҫ��ӵĵ��ʣ�");
				 Scanner Bin = new Scanner(System.in);
				 String BaddW = Bin.nextLine();
				 
				 System.out.println("����������Ҫ��ӵĵ������⣺");
				 Scanner Bin1 = new Scanner(System.in);
				 String BaddMean = Bin1.nextLine();
				 
				 int addKey = prepareForInsert(BaddW);
				 bt.insert(addKey, BaddW, BaddMean);
				 System.out.println("�Ѿ�������Ҫ��ӵĵ���"+BaddW+"��ӵ�B���У�");
			 }else if(BinputStr.equalsIgnoreCase("delw")){
				 System.out.println("����������Ҫɾ���ĵ��ʣ�");
				 Scanner Bin2 = new Scanner(System.in);
				 String deleteW = Bin2.nextLine();
				 int delKey = prepareForInsert(deleteW);
				 int res = bt.delete(delKey);//res��ֵΪ0ʱ����ʾ��ֵ����B����
				 if(res == 1)
					 System.out.println("�Ѿ�ɾ������Ҫɾ���ĵ���"+deleteW+"��B����ɾ����");
				 else {
					 System.out.println("�õ���"+deleteW+"����B���У�");
				}
			 }else if(BinputStr.equalsIgnoreCase("delf")){
//				 long time1 =  System.nanoTime()/1000L;
				 deleteFile(2);
//				 long time2 =  System.nanoTime()/1000L;
//				 System.out.println("\nB����ɾ���ļ� ������ʱ��Ϊ��"+(time2-time1)+"΢��");
				 System.out.println("�Ѿ��ɹ���ָ���ļ�2_delete�ڵ����ݴ�B����ɾ����");
			 }else if(BinputStr.equalsIgnoreCase("impo")){
				 
				 initial(2);
				 System.out.println("�Ѿ��ɹ����ƶ��ļ�3_insert�е����ݲ���B����");
			 }else if(BinputStr.equals("sers")){
				 System.out.println("��������ʼ���ʣ�");
				 Scanner scope2 = new Scanner(System.in);
				 String startStr2 = scope2.nextLine();
				 System.out.println("�������ֹ���ʣ�");
				 String endStr2 = scope2.nextLine();
				 
				 BsearchSome(prepareForInsert(startStr2), prepareForInsert(endStr2));
				 
			 }else {
				 if(BinputStr.equalsIgnoreCase("")){
				 }else{
//					 long time1 =  System.nanoTime()/1000L;
					 String re = bt.search(prepareForInsert(BinputStr));
//					 long time2 =  System.nanoTime()/1000L;
//					 System.out.println("\nB���в�ѯ���� ������ʱ��Ϊ��"+(time2-time1)+"΢��");
					 if(re.equals("")){
						 System.out.println("��Ǹ���ʿ���û������Ҫ�²�ѯ�Ĵʣ�");
					 }else{
						 String[] reArr = re.split("~");
						 System.out.println(reArr[1]);
						 
					 }
				 }
			 }
		 }catch (Exception e) {
			// TODO: handle exception
			 System.out.println("��ȷ������������ȷ!");
		}
		 
	  } 
		 
//		 �����벻��h����bʱ
	 }else 
		 System.out.println("��δѡ���κ����������ѽ�����");
	}
	
//	��ʼ���������B��
	public static void initial(int boo){
		 FileInputStream file = null;
		 InputStreamReader isr = null;
		 BufferedReader br = null; //
		 try {
			 String str = "";
			 if(boo==0||boo==3)
				 file = new FileInputStream("E:/xiaoming/AlgorithmsPj1/src/project1/1_initial.txt");// ��ָ�����ļ�·���¶�ȡ�ļ�
			 else if(boo==1||boo==2){
				 file = new FileInputStream("E:/xiaoming/AlgorithmsPj1/src/project1/3_insert.txt");// ��ָ�����ļ�·���¶�ȡ�ļ�
			}
	   // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
			 
			 isr = new InputStreamReader(file,"utf-8");// InputStreamReader ���ֽ���ͨ���ַ���������,
			 br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new InputStreamReader�Ķ���
			 br.readLine();
			 while ((str = br.readLine()) != null) {
				 if(boo==3||boo==2)
					 bt.insert(prepareForInsert(str),str,br.readLine());
				 else 
					 t.insert(prepareForInsert(str),str,br.readLine());
				 
//				 System.out.println(str);// ��ӡ��str
			 }
			 
		 } catch (FileNotFoundException e) {
			 System.out.println("�Ҳ���ָ���ļ�");
		 } catch (IOException e) {
			 System.out.println("��ȡ�ļ�ʧ��");
		 } finally {
			 try {
				 br.close();
				 isr.close();
				 file.close();
	    // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		 }
	}
//	����ɾ��
	public static void deleteFile(int de){
		 FileInputStream file = null;
		 InputStreamReader isr = null;
		 BufferedReader br = null; //
		 try {
			 String str = "";
			 file = new FileInputStream("E:/xiaoming/AlgorithmsPj1/src/project1/2_delete.txt");// ��ָ�����ļ�·���¶�ȡ�ļ�
	   // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
			 
			 isr = new InputStreamReader(file,"utf-8");// InputStreamReader ���ֽ���ͨ���ַ���������,
			 br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new InputStreamReader�Ķ���
			 br.readLine();
			 while ((str = br.readLine()) != null) {
				 if(de==1)
					 t.delete(prepareForInsert(str));
				 else 
					 bt.delete(prepareForInsert(str));
//				 System.out.println(str);// ��ӡ��str
//				 br.readLine();
			 }
			 
		 } catch (FileNotFoundException e) {
			 System.out.println("�Ҳ���ָ���ļ�");
		 } catch (IOException e) {
			 System.out.println("��ȡ�ļ�ʧ��");
		 } catch(Exception a){
//			System.out.println("youcuo"); 
		 }finally {
			 try {
				 br.close();
				 isr.close();
				 file.close();
	    // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		 }
	}
	
//	�������������ѯ
	public static void RBsearchSome(int startKey,int endKey){
		while(startKey<=endKey){
        	RBNode res = t.search(startKey);
        	if(res.getValue()!=null){
        		System.out.println(res.getKeyStr()+":"+res.getValue()+", ");
        	}
			startKey+=10;
		}
	}
//	B����������ѯ
	public static void BsearchSome(int startKey2,int endKey2){
		while(startKey2<=endKey2){
        	String res = bt.search(startKey2);
        	if(res.equals("")){
        	}else{
        		String[] resArr = res.split("~");
        		System.out.println(resArr[0]+":"+resArr[1]+", ");
        		res="";
        	}
			startKey2+=10;
			
		}
	}
	
//	����׼��
   public static int prepareForInsert(String keyStr){
	    int a = 0;
		for(int i=0;i<keyStr.length();i++){
			a += keyStr.charAt(i)*getPower(i);
		}
		return a;
//		System.out.println(a);
   }
//
   public static int getPower(int n){
	     int result = 1;
	     for(int i=0;i<n;i++){
	    	result *= 10; 
	     }
	     return result;
	  }
}
