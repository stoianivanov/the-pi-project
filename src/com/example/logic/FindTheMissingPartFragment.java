package com.example.logic;

import java.util.Random;
import com.android.*;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.example.thepiproject.R;


public class FindTheMissingPartFragment extends Fragment implements
		OnClickListener {

	private static final int MAX_QUESTIONS_TO_ASK = 10;

	OnAnswerSelectedListener answerSelected;

	int questionID;
	int answer1ID;
	int answer2ID;
	int asnwer3ID;
	int answer4ID;
	
	String prefix = "q";
	
	int correctAnswerID;

	int currentQuestionNumber;
	int numberOfAskedQuestions;
	int[] askedFindMissingPartQuestions;
	// int[] askedSpatialQuestions;
	int[] answerPositions;
	int answersOnPlace;

	ImageView questionView;
	ImageButton button1;
	ImageButton button2;
	ImageButton button3;
	ImageButton button4;

	@Override
	public void onStart() {
		super.onStart();
		askedFindMissingPartQuestions = new int[28];
		// askedSpatialQuestions = new int[15];
		answerPositions = new int[4];
		numberOfAskedQuestions = 0;

		answerSelected = (OnAnswerSelectedListener) getActivity();

		questionView = (ImageView) getView().findViewById(
				R.id.FindimageView_missing_part);
		button1 = (ImageButton) getView().findViewById(R.id.FindimageButton1);
		button1.setOnClickListener(this);
		button2 = (ImageButton) getView().findViewById(R.id.FindimageButton2);
		button2.setOnClickListener(this);
		button3 = (ImageButton) getView().findViewById(R.id.FindimageButton3);
		button3.setOnClickListener(this);
		button4 = (ImageButton) getView().findViewById(R.id.FindimageButton4);
		button4.setOnClickListener(this);

		nextQuestion();

	}

	
	public void nextQuestion() {
		getRandomNumber();
		getQuestionStrings();
		questionView.setImageResource(questionID);

		answersOnPlace = 0;
		for (int i = 0; i < answerPositions.length; i++) {
			answerPositions[i] = -1;
		}

		final int button1IDToSet = randomizeAnswerPositions();
		final int button2IDToSet = randomizeAnswerPositions();
		final int button3IDToSet = randomizeAnswerPositions();
		final int button4IDToSet = randomizeAnswerPositions();

		if (button1IDToSet == correctAnswerID) {
			button1.setTag("correct");
		} else {
			button1.setTag("incorect");
		}
		if (button2IDToSet == correctAnswerID) {
			button2.setTag("correct");
		} else {
			button2.setTag("incorect");
		}
		if (button3IDToSet == correctAnswerID) {
			button3.setTag("correct");
		} else {
			button3.setTag("incorect");
		}
		if (button4IDToSet == correctAnswerID) {
			button4.setTag("correct");
		} else {
			button4.setTag("incorect");
		}
		button1.setImageResource(button1IDToSet);
		button2.setImageResource(button2IDToSet);
		button3.setImageResource(button3IDToSet);
		button4.setImageResource(button4IDToSet);
	}

	
	
	private int randomizeAnswerPositions() {

		int ID = 0;
		Random rd = new Random();
		int random;
		boolean alreadySet;

		do {
			alreadySet = false;
			random = rd.nextInt(4);
			for (int i = 0; i < answerPositions.length; i++) {
				if (answerPositions[i] == random) {
					alreadySet = true;
					break;
				}
			}
			if (alreadySet == false) {
				answerPositions[answersOnPlace] = random;
				answersOnPlace++;
			}
		} while (alreadySet != false);

		switch (random) {
		case 0:
			ID = answer1ID;
			break;
		case 1:
			ID = answer2ID;
			break;
		case 2:
			ID = asnwer3ID;
			break;
		case 3:
			ID = answer4ID;
			break;
		}
		return ID;
	}

	private void getQuestionStrings() {

		
		if(numberOfAskedQuestions == 1){
			Intent i = new Intent(getActivity().getApplicationContext(),com.example.logic.NumbersExample.class);
			i.putExtra("example", 2);
			startActivity(i);
		}

		if (numberOfAskedQuestions == 6) {
			prefix = "s";
			Intent i = new Intent(getActivity().getApplicationContext(),com.example.logic.NumbersExample.class);
			i.putExtra("example", 3);
			startActivity(i);
		}
		final String question = prefix + currentQuestionNumber;
		final String answer1 = prefix + currentQuestionNumber + "a";
		final String answer2 = prefix + currentQuestionNumber + "b";
		final String answer3 = prefix + currentQuestionNumber + "c";
		final String answer4 = prefix + currentQuestionNumber + "d";

		Resources res = getActivity().getResources();
		String PACKAGE_NAME = getActivity().getApplicationContext()
				.getPackageName();
		Log.e("Package", PACKAGE_NAME);

		questionID = res.getIdentifier(PACKAGE_NAME + ":drawable/" + question,
				null, null);
		answer1ID = res.getIdentifier(PACKAGE_NAME + ":drawable/" + answer1,
				null, null);
		correctAnswerID = answer1ID;
		answer2ID = res.getIdentifier(PACKAGE_NAME + ":drawable/" + answer2,
				null, null);
		asnwer3ID = res.getIdentifier(PACKAGE_NAME + ":drawable/" + answer3,
				null, null);
		answer4ID = res.getIdentifier(PACKAGE_NAME + ":drawable/" + answer4,
				null, null);
	}

	private void getRandomNumber() {

		if (numberOfAskedQuestions == MAX_QUESTIONS_TO_ASK) {
			answerSelected.nextFragment();
		}
		Random rd = new Random();
		boolean isAsked = false;
		int random;
		do {
			isAsked = false;
			random = rd.nextInt(12);
			for (int i = 0; i < askedFindMissingPartQuestions.length; i++) {
				if (askedFindMissingPartQuestions[i] == random) {
					isAsked = true;
					break;
				}
			}
			if (isAsked == false) {
				askedFindMissingPartQuestions[numberOfAskedQuestions] = random;
				numberOfAskedQuestions++;
				currentQuestionNumber = random;
			}
		} while (isAsked != false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.find_the_missing_part_fragment,
				container, false);
	}

	@Override
	public void onClick(View v) {
		if (v.getTag().toString().equals("correct")) {
			answerSelected.correctAnswer();
		} else {
			answerSelected.wrongAnswer();
		}
		nextQuestion();

	}

	
}
