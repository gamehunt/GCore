package gamehunt.glib.utils;

public class Pair<F,S> {
	F f;
	S s;
	public Pair(F fr,S sc){
		f = fr;
		s = sc;
	}
	public F first(){
		return f;
	}
	
	public S second(){
		return s;
	}
	
	public void setFirst(F v){
		f = v;
	}
	
	public void setSecond(S v){
		s = v;
	}
	
	public  static <Ff,Ss> Pair<Ff,Ss> make_pair(Ff f,Ss s){
		return new Pair<Ff,Ss>(f,s);
	}
}
