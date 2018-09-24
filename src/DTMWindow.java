//　インポート
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;


public class DTMWindow {
	private JFrame jf;
	private Mascot m;
	
	//　コンストラクタ
	public DTMWindow() {
		createWindow();
		m = new Mascot(jf.getWidth(), jf.getHeight());
		jf.getContentPane().add(m.getPanel());
		m.putCenter();
		jf.repaint();
	}
	
	private void createWindow() {
		jf = new JFrame("DTMascot");
		jf.setSize(1280, 719);
	//	jf.setLocation(320, 180);
		jf.setAlwaysOnTop(true);
		
		//これを削除するとキャラを画面下で動かせます、常に画面の上にあるのが嫌な人は削除してください。
		jf.setUndecorated(true);
		
		jf.setBackground(new Color(0, 0, 0, 0));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		jf.setVisible(true);
	}
	
	public void start() {
		ActionListener al = new ActionListener() {
			@Override public void actionPerformed(ActionEvent e)
			{
				//定期的に実行したい処理
				m.move();
			}
		};
		Timer timer = new Timer(300,al);
		timer.start();
	}
}
