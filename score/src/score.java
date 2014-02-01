public class score{
	private static int constant = 126;
	private static double twitter_weight;
	private static double facebook_weight;
	private static int total_f;
	private static int friends;
	private static int followers;
	private static double twitter_score;
	private static double fb_score;
	private static int num_rtf;
	private static int num_like;
	public score(){}
	private static void set_total(int friends, int followers){
		total_f=followers+friends;
	}
	private static double set_twitter_weight(int followers, int total_f){
		twitter_weight = (double)(followers)/(double)(total_f);
		twitter_weight *= 100;
		return twitter_weight;
	}
	private static int calc_score_twitter(int num_rtf, int followers, double twitter_weight){
		twitter_score = (double)num_rtf/(double)followers;
		twitter_score *= constant;
		twitter_score *= twitter_weight;
		return (int)(twitter_score+0.5);
	}
	private static double set_fb_weight(int friends, int total_f){
		facebook_weight = (double)(friends)/(double)(total_f);
		facebook_weight *= 100;
		return facebook_weight;
	}
	private static int calc_score_fb(int num_like, int friends, double facebook_weight){
		fb_score = (double)num_like/(double)friends;
		fb_score *= constant;
		fb_score *= facebook_weight;
		return (int)(fb_score+0.5);
	}
	public static void main(String[] args){
		
	}
}