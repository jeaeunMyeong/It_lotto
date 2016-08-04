package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Lotto_game {
	private static ArrayList<Integer> lotto; 
	
	public static ArrayList<Integer> lotto_ran(){
		ArrayList<Integer> lotto_save;
		Set<Integer> set = new HashSet<>();
		
        while(set.size()< 6){
            Double d = (Math.random()*45+ 1);
            set.add(d.intValue());
        }
        lotto_save = new ArrayList<Integer>(set);
        //초기화하므로 다시 배열 첫번째부터 저장 하므로 이전 게임 데이터가 중복될 가능성 없음
        Collections.sort(lotto_save);
        return lotto_save;
	}

	public static void main(String[] args) {
		//동작확인용 메인함수
		
		ArrayList<Integer> arr=null;
		arr = lotto_ran();
		
		for(int i=0;i<6;i++){
			System.out.print(arr.get(i)+" ");
		}
	
	}
}
