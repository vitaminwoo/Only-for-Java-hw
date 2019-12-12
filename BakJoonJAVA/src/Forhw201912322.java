import java.util.*;
abstract class GameObject{
	protected int x,y;   // x, y 좌표
	public GameObject(int StartX, int StartY) {
		this.x = StartX;
		this.y = StartY;
	} //초기위치 설정해주는 생성자
	public int getX() {return x;}//위치를 알아 내주는 메소드
	public int getY() {return y;}//위치를 알아 내주는 메소드
	public boolean collide(GameObject p) {
		if(this.x == p.getX() && this.y == p.getY())
			return true;
		else
			return false;	
	}//플레이어가 장애물과 충돌 시 게임을 종료시키는 메소드
	public abstract void move();//이동시키는 메소드 move
	public abstract char getShape();//좌표에 해당하는 위치에 삽입하는 메소드
}

class Player extends GameObject{
	public Player(int StartX, int StartY) {
		super(StartX, StartY); //추상클래스를 통해 초기위치 설정
	}
	public void move() { //추상클래스 미구현 메소드 구현. 메소드 오버라이딩 생략.
		Scanner s = new Scanner(System.in);
		
		System.out.print("위(w), 왼쪽(a), 아래쪽(s), 오른쪽(d) >> ");
		String go = s.next();
		
		Game.arr[x][y] ='-'; //이동전의 위치에 공백 생성.
		
		switch(go) { //플레이어의 이동 구현.
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
		Game.arr[x][y] = getShape(); //이동후의 위치에 플레이어 배치.
	}
	public char getShape() { //추상클래스 미구현 메소드 구현. 메소드 오버라이딩 생략.
		return 'P';
	}
}

class Danger extends GameObject{
	public Danger(int StartX, int StartY) {
		super(StartX, StartY);//추상클래스를 통해 초기위치 설정
	}
	public void move() {//추상클래스 미구현 메소드 구현. 메소드 오버라이딩 생략.
		Game.arr[x][y] = '-';  //이동전의 위치에 공백 생성.
		y++; //오른쪽으로만 움직이는 장애물.
		if(y==20)
			y=1; //오른쪽 끝에 도달하면 다시 앞의 위치로.
		Game.arr[x][y] = getShape();
	}
	public char getShape() {//추상클래스 미구현 메소드 구현. 메소드 오버라이딩 생략.
		return '@';
	}
}
class Dx extends GameObject{
	public Dx(int StartX, int StartY) {
		super(StartX, StartY);//추상클래스를 통해 초기위치 설정
	}
	public void move() {}//추상클래스 미구현 메소드 구현. 메소드 오버라이딩 생략.
	public char getShape() {//추상클래스 미구현 메소드 구현. 메소드 오버라이딩 생략.
		return 'X';
	}
}
class Game{	
	Player p1;
	Danger danger;
	Dx dx1;	Dx dx2;	Dx dx3;	Dx dx4;	Dx dx5;	Dx dx6;	Dx dx7;	Dx dx8;
	//게임 요소들의 객체 생성.
	public static char arr[][] = new char[10][20];	//게임의 판의 크기.
	public void Set() {//초기좌표들 설정.
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
					arr[i][j] = '|'; //플레이어의 목적지 표현.
				else
					arr[i][j] = '-'; // 공백 표현.
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
	
	public void ShowArr() { //게임의 판 출력.
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	
	
	public void Run() {//게임 진행.
		Set(); //게임요소들의 좌표 설정.
		
		while(true) {
				if(p1.y==0) {//목적지 달성
					System.out.println("Escape SUCCESS!!");
					return;
				}
				//이후로는 장애물과의 충돌 확인.
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

				ShowArr(); //게임판 출력.
				p1.move(); //플레이어 이동.
				danger.move(); //동적 장애물 이동.
				System.out.println("남은 거리: "+p1.y);	 //목적지까지 남은 거리 계산.		
		}
	}
}
public class Forhw201912322 {
	public static void main(String[] args) {
		System.out.println("** 장애물 피해서 왼쪽으로 도망가기 게임! 시작합니다!**");
		Game game = new Game(); //게임 클래스의 객체 생성.
		game.Run(); //게임 진행 메소드 실행.
	}

}
