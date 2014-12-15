package com.example.thepiproject.speed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import com.example.logic.OnAnswerSelectedListener;
import com.example.thepiproject.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameSpeed1 extends Fragment implements OnClickListener {

	private final static int  MAX_QUESTION=5;
	private TextView  question;
	private EditText answer;
	private ImageButton next;
	private ArrayList<QuestionGame1> questions;
	private String answerFile;
	private OnAnswerSelectedListener listener;
	private int currentQuestion=0;
	private Button add1;
	private Button add2;
	private Button add3;
	private Button add4;
	private Button add5;
	private Button add6;
	private Button add7;
	private Button add8;
	private Button add9;
	private Button add0;
	private Button clear;
	private Button adddot;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_game_speed1, null);
		questions= new ArrayList<QuestionGame1>();
		answer= (EditText) view.findViewById(R.id.answerGame1);
		question= (TextView) view.findViewById(R.id.questionGame1);

		readFile();
		createQuestion();
		
		listener= (OnAnswerSelectedListener) getActivity();
		
		next= (ImageButton) view.findViewById(R.id.speedButtonOK);
		next.setOnClickListener(this);
		
		add1= (Button) view.findViewById(R.id.speedButtonAdd1);
		add1.setOnClickListener(this);
		
		add2= (Button) view.findViewById(R.id.speedButtonAdd2);
		add2.setOnClickListener(this);
		
		add3= (Button) view.findViewById(R.id.speedButtonAdd3);
		add3.setOnClickListener(this);
		
		add4= (Button) view.findViewById(R.id.speedButtonAdd4);
		add4.setOnClickListener(this);
		
		add5= (Button) view.findViewById(R.id.speedButtonAdd5);
		add5.setOnClickListener(this);
		
		add6= (Button) view.findViewById(R.id.speedButtonAdd6);
		add6.setOnClickListener(this);
		
		add7= (Button) view.findViewById(R.id.speedButtonAdd7);
		add7.setOnClickListener(this);
		
		add8= (Button) view.findViewById(R.id.speedButtonAdd8);
		add8.setOnClickListener(this);
		
		add9= (Button) view.findViewById(R.id.speedButtonAdd9);
		add9.setOnClickListener(this);
		
		adddot= (Button) view.findViewById(R.id.speedButtonAdd_);
		adddot.setOnClickListener(this);
		
		clear= (Button) view.findViewById(R.id.speedButtonClear);
		clear.setOnClickListener(this);
		return view;
	}
	private void createQuestion(){
		QuestionGame1 q = getQuestion();
		question.setText(q.getQuestion());
		answerFile=q.getAnswer();
		++currentQuestion;
	}
	private QuestionGame1 getQuestion(){
		Random r= new Random();
		
		int index = r.nextInt(questions.size());
		Log.i("Ranodm",""+index);
		QuestionGame1 q = questions.get(index);
		questions.remove(index);
		return q;
	}
	private void nextQuestion(){
		if(currentQuestion==MAX_QUESTION){
			listener.nextFragment();
		}
		createQuestion();
		
	}
	private void readFile(){
		final InputStream is = this.getResources().openRawResource(
				R.raw.question_game_1);
		Log.i("ReadFile", "open input stream");
		final BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		Log.i("ReadFile", "open BUffered Reader");
		int pos=0;
		String line;
		try {
			while((line=bf.readLine())!=null){
				QuestionGame1 	questionGame= new QuestionGame1();
				questionGame.setQuestion(line);
				line=bf.readLine();
				questionGame.setAnswer(line);
				questions.add(pos, questionGame);
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
	@Override
	public void onClick(View v) {
		int id= v.getId();
		if(id==R.id.speedButtonOK){
			if(answer.getText().toString().equals(answerFile)){
				listener.correctAnswer();
				answer.setText("");
				nextQuestion();
			}else{
				listener.wrongAnswer();
				answer.setText("");
				nextQuestion();
			}	
			
		}


		if(id==R.id.speedButtonAdd0){
			if(!answer.getText().toString().equals("")){
				answer.setText(answer.getText().toString()+"0");
			}
			
		}
		if(id==R.id.speedButtonAdd1){
			answer.setText(answer.getText().toString()+"1");
		}
		
		if(id==R.id.speedButtonAdd2){
			answer.setText(answer.getText().toString()+"2");
		}
		if(id==R.id.speedButtonAdd3){
			answer.setText(answer.getText().toString()+"3");
		}
		if(id==R.id.speedButtonAdd4){
			answer.setText(answer.getText().toString()+"4");
		}
		if(id==R.id.speedButtonAdd5){
			answer.setText(answer.getText().toString()+"5");
		}
		if(id==R.id.speedButtonAdd6){
			answer.setText(answer.getText().toString()+"6");
		}
		if(id==R.id.speedButtonAdd7){
			answer.setText(answer.getText().toString()+"7");
		}
		if(id==R.id.speedButtonAdd8){
			answer.setText(answer.getText().toString()+"8");
		}
		if(id==R.id.speedButtonAdd9){
			answer.setText(answer.getText().toString()+"9");
		}
		if(id==R.id.speedButtonClear){
			answer.setText("");
		}
		if(id==R.id.speedButtonAdd_){
			answer.setText(".");
		}
		
		
		
		
		
		
		
		
	}
}
