// インポート
import java.awt.Color;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Mascot {
	
	// 画像ファイル名
	private static final String    IMAGE_NAME = "画像ファイル";
	// 横方向に並んでいるキャラクターの数
	private static final int    COLS = 4;
	//　縦方向に並んでいるキャラクターの数
	private static final int    ROWS = 4;
	//　横方向の移動距離
	private static final int    DX = (int)(6.4 * 4);
	//　縦方向の移動距離
	private static final int    DY = 2 * 4;
	
	private JPanel jp;
	private JLabel jl;
	private Random r = new Random();
	
	//　画像を表示するときのX座標
	private int[]  imageX = new int[COLS];
	// 画像を表示するときのY座標
	private int[]  imageY = new int[ROWS];
	// 表示している画像
	private int    showingImage;
	//　マスコットがいけるX座標の最大値
	private int maxX;
	// マスコットがいけるY座標の最大値
	private int maxY;
	
	//　コンストラクタ
	public Mascot(int windowWidth, int windowHeight) {
		// 画像のファイル名からImageIconを作成
		ImageIcon ii = new ImageIcon(IMAGE_NAME);
		int imageWidth = ii.getIconWidth();    //画像の横幅
		int imageHeight = ii.getIconHeight();  //画像の高さ
		
		//　ImageIconを含んでいるJLabelを生成
		jl = new JLabel(ii);
		jl.setSize(imageWidth,imageHeight);
		
		// マスコット一体分の横幅
		int mascotWidth = imageWidth / COLS;
		// マスコット一体分の高さ
		int mascotHeight = imageHeight / ROWS;
		
		for(int i = 0; i < COLS; ++i) {
			imageX[i] = mascotWidth * -i;
		}
		for(int i = 0; i < ROWS; ++i) {
			imageY[i] = mascotHeight * -i;
		}
		
		// JPanelを作成
		jp = new JPanel();
		jp.setLayout(null);
		jp.setSize(mascotWidth,mascotHeight);
		jp.setBackground(new Color(0, 0, 0, 0));
		jp.add(jl);
		
		maxX = windowWidth - mascotWidth;
		maxY = windowHeight - mascotHeight;	
	}
	public Mascot() {
		//　ほかのコンストラクタを呼び出す
		this(1280, 720);
	}
	
	public JPanel getPanel() {
		return jp;
	}
	
	//　休止する時間
	private int sleep;
	// 歩数
	private int step;
	// 歩く方向
	private int way;
	
	public void move() {
		if(sleep > 0) {
			--sleep;
		}else { // sleepがゼロ以下なら行動
			action();
			
		}
	}
	
	private void action() {
		if(step > 0) {
			walk();
			--step;
		}else {
			stop();
			setNewValue();
			
		}
	}
	
	private void setNewValue() {
		sleep = r.nextInt(35);
		step = r.nextInt(11) * 2;
		way = r.nextInt(ROWS);
	}
	private int x;
	private int y;
	
	private void walk() {
		++showingImage;
		if(showingImage >= COLS) {
			showingImage = 0;
		}
		jl.setLocation(imageX[showingImage], imageY[way]);
		
		switch(way) {
		case 0:{ // ↓
			y += DY;
			if(y > maxY){
				y = maxY;
				step = 0;
			}
			break;
			
		}case 1:{ //　←
			x -= DX;
			if(x < 0) {
				x = 0;
				step = 0;
			}
			break;
			
		}case 2:{ // →
			x += DX;
			if(x > maxX) {
				x = maxX; 
				step = 0;
			}
			break;
			
		}case 3:{ //　↑
			y -= DY;
			if(y < 0){
				y = 0;
				step = 0;
			}
			break;
			
		}default:{	
		}
		}// switch_END
		
		jp.setLocation(x, y);
		
	}
		
		private void stop() {
			step = 0;
			jl.setLocation(imageX[0], imageY[way]);
			jp.getParent().repaint();
			showingImage = 0;
			step = 0;
		}
		public void putRandom() {
			x = r.nextInt(maxX);
			y = r.nextInt(maxY);
			jp.setLocation(x, y);
		}
		
		
		public void putCenter() {
			x = jp.getParent().getWidth() / 2 - jp.getWidth() / 2;
			y = jp.getParent().getHeight() / 2 - jp.getHeight() / 2;
			jp.setLocation(x, y);
		}
}
