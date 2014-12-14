package com.example.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.thepiproject.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NumbersFragment extends Fragment implements OnClickListener,OnFocusChangeListener{

	private static final int MAX_NUMBER_OF_QUESTIONS = 5;
	
	
	Button answerButton;
	OnAnswerSelectedListener answerSelected;
	int questionPosition;
	TextView question;
	EditText answerText;
	Numbers current;
	int numberOfAskedQuestions;
	List<Numbers> numbers;

	private void getRandomQuestionPosition(){
		Random rd = new Random();
		questionPosition = rd.nextInt(numbers.size());
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		
		answerSelected = (OnAnswerSelectedListener) getActivity();
		answerButton = (Button) getView().findViewById(R.id.answerNumericalSeries);
		answerButton.setOnClickListener(this);
		numbers = new ArrayList<Numbers>(30);
		openFile();
		answerText = (EditText) getView().findViewById(R.id.answer);
		
		answerText.setOnFocusChangeListener(this);
		
		numberOfAskedQuestions=0;
		question = (TextView) getView().findViewById(R.id.numericProblem);
		nextQuestion();
	}
	
	
	public void nextQuestion(){
		if(numberOfAskedQuestions == MAX_NUMBER_OF_QUESTIONS){
			answerSelected.nextFragment();
		}
		getRandomQuestionPosition();
		current = numbers.get(questionPosition);
		Log.e("Question",current.toString());
		question.setText(current.getQuestion());
		numberOfAskedQuestions++;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.logic_fragment,container,false);
	}

	private void openFile() {

		final InputStream is = this.getResources().openRawResource(
				R.raw.numbers);
		final BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		int counter = 0;
		String line;
		try {
			while ((line = bf.readLine()) != null) {
				Numbers num = new Numbers();
				num.setQuestion(line);
				Log.e("Reader",line);
				line = bf.readLine();
				num.setAnswer(line);
				Log.e("Reader",line);
				numbers.add(counter, num);
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void onClick(View v) {
		
		
		
		if(v.getId() == R.id.answerNumericalSeries){
			String answer = answerText.getText().toString();
			if(answer.equals(current.getAnswer())){
				answerSelected.correctAnswer();
				numbers.remove(questionPosition);
				nextQuestion();
				answerText.setText("");
			} else {
				answerSelected.wrongAnswer();
				numbers.remove(questionPosition);
				nextQuestion();
				answerText.setText("");
				
			}
		}
	}

	private void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
	}
	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if(v.getId() == R.id.answer){
			if(hasFocus){
				hideKeyboard();
			}
		}
	}
	
}
