public class score{
	private static int constant = 10000;
	private static double twitter_weight;
	private static double facebook_weight;
	private static int total_f;
	private static int friends;
	private static int followers;
	private static double dbl_twitter_score;
	private static double dbl_fb_score;
	private static int twitter_score;
	private static int fb_score;
	private static int num_rtf;
	private static int num_like;
	public score(){}
	private static void set_total(){
		total_f=followers+friends;
	}
	private static void set_twitter_weight(){
		twitter_weight = (double)(followers)/(double)(total_f);
		twitter_weight *= 100;
	}
	private static void calc_score_twitter(){
		dbl_twitter_score = (double)num_rtf/(double)followers;
		dbl_twitter_score *= constant;
		dbl_twitter_score *= twitter_weight;
		twitter_score = (int)(dbl_twitter_score+0.5);
	}
	private static void set_fb_weight(){
		facebook_weight = (double)(friends)/(double)(total_f);
		facebook_weight *= 100;
	}
	private static void calc_score_fb(){
		dbl_fb_score = (double)num_like/(double)friends;
		dbl_fb_score *= constant;
		dbl_fb_score *= facebook_weight;
		fb_score = (int)(dbl_fb_score+0.5);
	}
	public static void get_score(int numt, int tf, int numl, int fbf){
		num_rtf = numt;
		followers = tf;
		num_like = numl;
		friends = fbf;
		set_total();
		set_twitter_weight();
		set_fb_weight();
		calc_score_twitter();
		calc_score_fb();
		StdOut.println("Twitter score: " + twitter_score + " Facebook score: " + fb_score);
	}
}