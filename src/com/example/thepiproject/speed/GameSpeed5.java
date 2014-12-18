package com.example.thepiproject.speed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.logic.NumbersExample;
import com.example.logic.OnAnswerSelectedListener;
import com.example.thepiproject.R;
import com.example.thepiproject.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameSpeed5 extends Fragment implements OnClickListener {


	private static final int MAX_QUESTION = 5;
	private Button answer1;
	private Button answer2;
	private Button answer3;
	private Button answer4;
	private TextView questionText;
	private ArrayList<QuestionGame2> question;
	private QuestionGame2 currentQuestion;
	private OnAnswerSelectedListener listener;
	private int currentQ=0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view= inflater.inflate(R.layout.activity_game_speed5, null);
		Intent i = new Intent(getActivity().getApplicationContext(),NumbersExample.class);
		i.putExtra("example", 5);
		startActivity(i);
		question= new ArrayList<QuestionGame2>();
		listener= (OnAnswerSelectedListener) getActivity();

		
		answer1= (Button) view.findViewById(R.id.Answer1);
		answer1.setOnClickListener(this);
		
		answer2= (Button) view.findViewById(R.id.Answer2);
		answer2.setOnClickListener(this);
		
		answer3= (Button) view.findViewById(R.id.Answer3);
		answer3.setOnClickListener(this);
		
		answer4= (Button) view.findViewById(R.id.Answer4);
		answer4.setOnClickListener(this);
		
		questionText=(TextView) view.findViewById(R.id.questionGame5);
		readFile();
		createFragment();
		return view;
	}

	@Override
	public void onClick(View v) {
		int id= v.getId();

		if(id==R.id.Answer4){
			Answer(answer4);
		}
		
		if(id==R.id.Answer1){
			Answer(answer1);
		}
		
		if(id==R.id.Answer2){
			
			Answer(answer2);
		}
		
		if(id==R.id.Answer3){
			Answer(answer3);
		}
		
	}
	private void nextQuestion(){
		if(currentQ==MAX_QUESTION){
			listener.nextFragment();
		}else{
			createFragment();
		}
		
		
	}
	private void Answer(Button b){
		if(b.getText().equals(currentQuestion.getCorrectAnswer())){
			listener.correctAnswer();
		}else{
			listener.wrongAnswer();
		}
		nextQuestion();
	}
	public void createFragment(){
		
		currentQuestion= getQuestion();
		questionText.setText(currentQuestion.getQuestion());
		answer1.setText(currentQuestion.getAnswer1());
		answer2.setText(currentQuestion.getAnswer2());
		answer3.setText(currentQuestion.getAnswer3());
		answer4.setText(currentQuestion.getAnswer4());	
		
		++currentQ;
		if(currentQ>MAX_QUESTION){
			listener.nextFragment();
		}
		
	}
	private QuestionGame2 getQuestion(){
		Random r= new Random();
		
		int index = r.nextInt(question.size());
		Log.i("Ranodm",""+index);
		QuestionGame2 q = question.get(index);
		question.remove(index);
		return q;
	}
	private void readFile(){
		final InputStream is = this.getResources().openRawResource(
				R.raw.question_game_2);
		final BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		int pos = 0;
		String line;
		try {
			while((line=bf.readLine())!=null){
				QuestionGame2 game2= new QuestionGame2();
				game2.setQuestion(line);
				line=bf.readLine();
				game2.setCorrectAnswer(line);
				line=bf.readLine();
				game2.setAnswer1(line);
				line=bf.readLine();
				game2.setAnswer2(line);
				line=bf.readLine();
				game2.setAnswer3(line);
				line=bf.readLine();
				game2.setAnswer4(line);
				question.add(pos, game2);
				++pos;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				is.close();
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
