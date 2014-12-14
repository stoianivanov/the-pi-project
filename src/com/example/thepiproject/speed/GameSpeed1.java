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
import android.widget.TextView;

public class GameSpeed1 extends Fragment {

	private final static int  MAX_QUESTION=5;
	private TextView  question;
	private EditText answer;
	private Button next;
	private ArrayList<QuestionGame1> questions;
	private String answerFile;
	private OnAnswerSelectedListener listener;
	private int currentQuestion=0;
	
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
		
		next= (Button) view.findViewById(R.id.nextGame1);
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
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
		});
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
}
