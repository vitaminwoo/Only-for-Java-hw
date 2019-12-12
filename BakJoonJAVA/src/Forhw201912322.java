import java.util.*;
abstract class GameObject{
	protected int x,y;   // x, y ��ǥ
	public GameObject(int StartX, int StartY) {
		this.x = StartX;
		this.y = StartY;
	} //�ʱ���ġ �������ִ� ������
	public int getX() {return x;}//��ġ�� �˾� ���ִ� �޼ҵ�
	public int getY() {return y;}//��ġ�� �˾� ���ִ� �޼ҵ�
	public boolean collide(GameObject p) {
		if(this.x == p.getX() && this.y == p.getY())
			return true;
		else
			return false;	
	}//�÷��̾ ��ֹ��� �浹 �� ������ �����Ű�� �޼ҵ�
	public abstract void move();//�̵���Ű�� �޼ҵ� move
	public abstract char getShape();//��ǥ�� �ش��ϴ� ��ġ�� �����ϴ� �޼ҵ�
}

class Player extends GameObject{
	public Player(int StartX, int StartY) {
		super(StartX, StartY); //�߻�Ŭ������ ���� �ʱ���ġ ����
	}
	public void move() { //�߻�Ŭ���� �̱��� �޼ҵ� ����. �޼ҵ� �������̵� ����.
		Scanner s = new Scanner(System.in);
		
		System.out.print("��(w), ����(a), �Ʒ���(s), ������(d) >> ");
		String go = s.next();
		
		Game.arr[x][y] ='-'; //�̵����� ��ġ�� ���� ����.
		
		switch(go) { //�÷��̾��� �̵� ����.
		case "a":
			if(y==0)
				y=0;
			else
				y-=1;
			break;
		case "s":
			if(x==9)
				x=9;
			else
				x+=1;
			break;
		case "w":
			if(x==0)
				x=0;
			else
				x-=1;
			break;
		case "d":
			if(y==19)
				y=19;
			else
				y+=1;	
			break;
		default:
			System.out.println("ERROR!!");
		}
		Game.arr[x][y] = getShape(); //�̵����� ��ġ�� �÷��̾� ��ġ.
	}
	public char getShape() { //�߻�Ŭ���� �̱��� �޼ҵ� ����. �޼ҵ� �������̵� ����.
		return 'P';
	}
}

class Danger extends GameObject{
	public Danger(int StartX, int StartY) {
		super(StartX, StartY);//�߻�Ŭ������ ���� �ʱ���ġ ����
	}
	public void move() {//�߻�Ŭ���� �̱��� �޼ҵ� ����. �޼ҵ� �������̵� ����.
		Game.arr[x][y] = '-';  //�̵����� ��ġ�� ���� ����.
		y++; //���������θ� �����̴� ��ֹ�.
		if(y==20)
			y=1; //������ ���� �����ϸ� �ٽ� ���� ��ġ��.
		Game.arr[x][y] = getShape();
	}
	public char getShape() {//�߻�Ŭ���� �̱��� �޼ҵ� ����. �޼ҵ� �������̵� ����.
		return '@';
	}
}
class Dx extends GameObject{
	public Dx(int StartX, int StartY) {
		super(StartX, StartY);//�߻�Ŭ������ ���� �ʱ���ġ ����
	}
	public void move() {}//�߻�Ŭ���� �̱��� �޼ҵ� ����. �޼ҵ� �������̵� ����.
	public char getShape() {//�߻�Ŭ���� �̱��� �޼ҵ� ����. �޼ҵ� �������̵� ����.
		return 'X';
	}
}
class Game{	
	Player p1;
	Danger danger;
	Dx dx1;	Dx dx2;	Dx dx3;	Dx dx4;	Dx dx5;	Dx dx6;	Dx dx7;	Dx dx8;
	//���� ��ҵ��� ��ü ����.
	public static char arr[][] = new char[10][20];	//������ ���� ũ��.
	public void Set() {//�ʱ���ǥ�� ����.
		p1 = new Player(5,19);
		danger = new Danger((int)(Math.random()*10),1);
		dx1 = new Dx((int)(Math.random()*10),1+(int)(Math.random()*18));
		dx2 = new Dx((int)(Math.random()*10),1+(int)(Math.random()*18));
		dx3 = new Dx((int)(Math.random()*10),1+(int)(Math.random()*18));
		dx4 = new Dx((int)(Math.random()*10),1+(int)(Math.random()*18));
		dx5 = new Dx((int)(Math.random()*10),1+(int)(Math.random()*18));
		dx6 = new Dx((int)(Math.random()*10),1+(int)(Math.random()*18));
		dx7 = new Dx((int)(Math.random()*10),1+(int)(Math.random()*18));
		dx8 = new Dx((int)(Math.random()*10),1+(int)(Math.random()*18));

		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				if(j==0) 
					arr[i][j] = '|'; //�÷��̾��� ������ ǥ��.
				else
					arr[i][j] = '-'; // ���� ǥ��.
			}
		}
		arr[p1.x][p1.y] = p1.getShape();
		arr[danger.x][danger.y] = danger.getShape();
		arr[dx1.x][dx1.y] = dx1.getShape();
		arr[dx2.x][dx2.y] = dx2.getShape();
		arr[dx3.x][dx3.y] = dx3.getShape();
		arr[dx4.x][dx4.y] = dx4.getShape();
		arr[dx5.x][dx5.y] = dx5.getShape();
		arr[dx6.x][dx6.y] = dx6.getShape();
		arr[dx7.x][dx7.y] = dx7.getShape();
		arr[dx8.x][dx8.y] = dx8.getShape();


	}
	
	public void ShowArr() { //������ �� ���.
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	
	
	public void Run() {//���� ����.
		Set(); //���ӿ�ҵ��� ��ǥ ����.
		
		while(true) {
				if(p1.y==0) {//������ �޼�
					System.out.println("Escape SUCCESS!!");
					return;
				}
				//���ķδ� ��ֹ����� �浹 Ȯ��.
				if((p1.collide(danger)) == true) {
					System.out.println("YOU DEAD!!");
					return;
				}
				if((p1.collide(dx1)) == true) {
					System.out.println("YOU DEAD!!");
					return;
				}
				if((p1.collide(dx2)) == true) {
					System.out.println("YOU DEAD!!");
					return;
				}
				if((p1.collide(dx3)) == true) {
					System.out.println("YOU DEAD!!");
					return;
				}
				if((p1.collide(dx4)) == true) {
					System.out.println("YOU DEAD!!");
					return;
				}
				if((p1.collide(dx5)) == true) {
					System.out.println("YOU DEAD!!");
					return;
				}
				if((p1.collide(dx6)) == true) {
					System.out.println("YOU DEAD!!");
					return;
				}
				if((p1.collide(dx7)) == true) {
					System.out.println("YOU DEAD!!");
					return;
				}
				if((p1.collide(dx8)) == true) {
					System.out.println("YOU DEAD!!");
					return;
				}

				ShowArr(); //������ ���.
				p1.move(); //�÷��̾� �̵�.
				danger.move(); //���� ��ֹ� �̵�.
				System.out.println("���� �Ÿ�: "+p1.y);	 //���������� ���� �Ÿ� ���.		
		}
	}
}
public class Forhw201912322 {
	public static void main(String[] args) {
		System.out.println("** ��ֹ� ���ؼ� �������� �������� ����! �����մϴ�!**");
		Game game = new Game(); //���� Ŭ������ ��ü ����.
		game.Run(); //���� ���� �޼ҵ� ����.
	}

}
