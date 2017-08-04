package project1;

public class RBTree {
	private RBNode root;
//	����nil
	private  RBNode nil = new RBNode(null,null,null,0,-1,null,null);
	
	
//	�����ɫ�ͺ�ɫ
	public static final int BLACK = 0;
	public static final int RED = 1;
    public RBTree(){
    	root = nil;
    }
    
    public RBTree(RBNode root){
    	this.root = root;
    }
    
    public void insert(int key,String keyStr,String value){
    	RBNode a = new RBNode(key,keyStr,value);
    	RBInsert(a);
    }
//���뷽��
	public void RBInsert(RBNode z){
		RBNode y = nil;
		RBNode x = root;
			
//		�ҵ�zӦ���ڵ�λ�õĸ��׽ڵ�y
		while (x!=nil){
			y = x;
			if(z.getKey()<x.getKey()){
				x = x.getLeft();
			}else 
				x = x.getRight();
		}
//		��z�ĸ���ָ��y
		z.setParent(y);
		if(y == nil){
			root = z;
		}else if(z.getKey()<y.getKey()){
			y.setLeft(z);
		}else 
			y.setRight(z);
		z.setLeft(nil);
		z.setRight(nil);
		z.setColor(RED);
			
//		FIXUP function
		RBInsertFixup(z);
		
	}
		
//	�����ĵ�������
	public void RBInsertFixup(RBNode z){
		while(z.getParent().getColor()==RED){
			if(z.getParent()==z.getParent().getParent().getLeft()){//z�ĸ�����z���游�������
				RBNode y = z.getParent().getParent().getRight();
				if(y.getColor()==RED){
					z.getParent().setColor(BLACK);
					y.setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					z = z.getParent().getParent();
				}else if(z==z.getParent().getRight()){
						z = z.getParent();
						LeftRotation(z);
					}else{
						z.getParent().setColor(BLACK);
						z.getParent().getParent().setColor(RED);
						RightRotation(z.getParent().getParent());
				}
			}else{//z�ĸ��������游���Ҷ���
				RBNode y = z.getParent().getParent().getLeft();
				if(y.getColor()==RED){
					z.getParent().setColor(BLACK);
					y.setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					z = z.getParent().getParent();
				}else if(z==z.getParent().getLeft()){
						z = z.getParent();
						RightRotation(z);
					}else{
						z.getParent().setColor(BLACK);
						z.getParent().getParent().setColor(RED);
						LeftRotation(z.getParent().getParent());
				}
			}
		}
		root.setColor(BLACK);
	}
		
//	����ת����
	public void LeftRotation(RBNode x){
		RBNode y = x.getRight();   //set y
		x.setRight(y.getLeft());     //turn y's left subtree into x's right subtree
		if(y.getLeft()!= nil){
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());  //link x's parent to y
		if(x.getParent() == nil){
			root = y;
		}else if(x==x.getParent().getLeft()){
			x.getParent().setLeft(y);
		}else {
			x.getParent().setRight(y);
		}
		y.setLeft(x);         //put x on y's left
		x.setParent(y);
	}
//	����ת����
	public void RightRotation(RBNode x){
		RBNode y = x.getLeft();   //set y
		x.setLeft(y.getRight());     //turn y's right subtree into x's left subtree
//		System.out.println((!y.getRight().equals(nil))+""+y.getKey()+"color");
		if(y.getRight()!=nil){
			y.getRight().setParent(x);
//			System.out.println("fffff "+y.getRight().getKey());
		}
		y.setParent(x.getParent());    //link x's parent to y
		if(x.getParent() == nil){
			root = y;
		}else if(x==x.getParent().getRight()){
			x.getParent().setRight(y);
		}else {
			x.getParent().setLeft(y);
		}
		y.setRight(x);          //put x on y's right
		x.setParent(y); 
	}
		
//	��������
	public RBNode search(int key){
		RBNode x = root;   //��ʼ�����ĵط���Ϊx
		while((x!= nil)&&(key!=x.getKey())){
			if (key<x.getKey()){
				x=x.getLeft();
			}else 
				x=x.getRight();
		}
		return x;
	}
		
//		�����ڵ�ת�ƣ�Ϊɾ�����̵��õ��ӹ��̣�
		public void RBTransplant(RBNode u,RBNode v){
//			��u��v���滻
			if(u.getParent() == nil){
				root = u;
			}else if(u==u.getParent().getLeft()){
				u.getParent().setLeft(v);
			}else {
				u.getParent().setRight(v);
			}
			v.setParent(u.getParent());
		}
//		Ѱ����С��Ԫ�صķ���
		public RBNode TreeMinimum(RBNode x){
			while(x.getLeft()!= nil){
				x = x.getLeft();
			}
			return x;
		}
		
		public int delete(int key){
			RBNode a = search(key);
			if(a==nil)
				return -1;
			else {
				RBDelete(a);
				return 0;
			}
		}
//		ɾ������
		public void RBDelete(RBNode z){
			RBNode y = z;
			int yOriginalColor = y.getColor();//��¼��z�ĳ�ʼ��ɫ
			RBNode x ;
			if (z.getLeft()==nil){//�����Ϊ��ʱ
				x = z.getRight();
				RBTransplant(z, z.getRight());
			}else if(z.getRight()==nil){//�Ҷ���Ϊ��ʱ
				x = z.getLeft();
				RBTransplant(z, z.getLeft());
			}else {                //�����Ҷ��Ӷ���Ϊ��ʱ
//				System.out.println("zdeyou��"+z.getRight().getKey());
//				�ҵ�z�ĺ��
				y = TreeMinimum(z.getRight());
				yOriginalColor = y.getColor();
				x = y.getRight();
				if(y.getParent()==z){
					x.setParent(y);
				}else{
					RBTransplant(y, y.getRight());
					y.setRight(z.getRight());
					y.getRight().setParent(y);
				}
				RBTransplant(z, y);
				y.setLeft(z.getLeft());
				y.getLeft().setParent(y);
				y.setColor(z.getColor());
			}
			if(yOriginalColor == BLACK){
				RBDeleteFixup(x);
			}
		}
//		ɾ����ĵ���
		public void RBDeleteFixup(RBNode x){
			while((x!=root)&&(x.getColor()==BLACK)){
//				System.out.println("xde "+x.getParent());
				if(x==(x.getParent().getLeft())){   //��x���丸�׵������ʱ
					RBNode w = x.getParent().getRight();  //w�ǵ�sibling
					if(w.getColor()==RED){   //w����ɫΪ��ɫʱ
						w.setColor(BLACK);
						x.getParent().setColor(RED);
						LeftRotation(x.getParent());
						w = x.getParent().getRight();
					}
	                if(w.getLeft().getColor() == BLACK&&w.getRight().getColor() == BLACK){//��w�����Ҷ��Ӷ��Ǻ�ɫ��ʱ��
	                	w.setColor(RED);
	                	x=x.getParent();
	                }else if(w.getRight().getColor()==BLACK){                   //��w���Ҷ��ӵ���ɫ�Ǻ�ɫ��ʱ��
	                	w.getLeft().setColor(BLACK);
	                	w.setColor(RED);
	                	RightRotation(w);
	                	w = x.getParent().getRight();
	                }
	                w.setColor(x.getParent().getColor());
	                x.getParent().setColor(BLACK);
	                w.getRight().setColor(BLACK);
	                LeftRotation(x.getParent());
	                x=root;
	                }else{      //��x���丸�׵��Ҷ���ʱ
	                	RBNode w = x.getParent().getLeft();  //w�ǵ�sibling
	    				if(w.getColor()==RED){   //w����ɫΪ��ɫʱ
	    					w.setColor(BLACK);
	    					x.getParent().setColor(RED);
	    					LeftRotation(x.getParent());
	    					w = x.getParent().getLeft();
	    				}
	                    if(w.getRight().getColor() == BLACK&&w.getLeft().getColor() == BLACK){//��w�����Ҷ��Ӷ��Ǻ�ɫ��ʱ��
	                    	w.setColor(RED);
	                    	x=x.getParent();
	                    }else if(w.getLeft().getColor()==BLACK){                   //��w���Ҷ��ӵ���ɫ�Ǻ�ɫ��ʱ��
	                    	w.getRight().setColor(BLACK);
	                    	w.setColor(RED);
	                    	RightRotation(w);
	                    	w = x.getParent().getLeft();
	                    }
	                    w.setColor(x.getParent().getColor());
	                    x.getParent().setColor(BLACK); 
	                    w.getLeft().setColor(BLACK);
	                    LeftRotation(x.getParent());
	                    x=root;
	                }
			}
			x.setColor(BLACK);
		}
		
		//���������
		public void printTree() {
			System.out.println("\n�������:\n");
			preOrder_print(root);
			System.out.println("\n�������:\n");
			inOrder_print(root);
			System.out.println("\n�������:\n");
			postOrder_print(root);
		}
//		�������
		public void preOrder_print(RBNode node){
			if(node != nil){
				System.out.println(" �ڵ�ؼ��֣�"+node.getKeyStr() + " ��ɫΪ��" + getColorOfNode(node.getColor())+" ֵΪ��"+node.getValue());
				preOrder_print(node.getLeft());
				preOrder_print(node.getRight());
			}
		}
//		�������
		public void inOrder_print(RBNode node) {
			if (node != nil) {
				inOrder_print(node.getLeft());
				System.out.println(" �ڵ�ؼ��֣�"+node.getKeyStr() + " ��ɫΪ��" +getColorOfNode(node.getColor())+" ֵΪ��"+node.getValue());
				inOrder_print(node.getRight());
			}
		}
//		�������
		public void postOrder_print(RBNode node){
			if(node != nil){
				postOrder_print(node.getLeft());
				postOrder_print(node.getRight());
				System.out.println(" �ڵ�ؼ��֣�"+node.getKeyStr() + " ��ɫΪ��" + getColorOfNode(node.getColor())+" ֵΪ��"+node.getValue());
			}
		}
//		�õ���ɫ
		public static String getColorOfNode(int color0){
			String re = "";
			if(color0==0){
				re = "BLACK";
			}else 
				re= "RED";
			return re;
		}
		
		public static void main(String[] args){
			int[] aa = {10,2,12,4,6,8,1,9,7,3,11,5};
			RBTree T = new RBTree();
			System.out.println("����֮ǰ�������ǣ�");
			for(int i=0;i<aa.length;i++){
//				RBNode a = new RBNode(aa[i],"sss");
				T.insert(aa[i],aa[i]+"","sss");
				System.out.print(aa[i]+",");
			}
			T.printTree();
			System.out.println("ɾ���ؼ���3��2��9��4��8֮��");
			T.delete(3);
			T.delete(2);
			T.delete(9);
			T.delete(4);
			T.delete(8);
			T.printTree();
		}
		public RBNode getRoot() {
			return root;
		}
		public void setRoot(RBNode root) {
			this.root = root;
		}
		public RBNode getNil() {
			return nil;
		}
		public void setNil(RBNode nil) {
			this.nil = nil;
		}
	
}


