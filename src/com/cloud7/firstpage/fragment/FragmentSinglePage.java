package com.cloud7.firstpage.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.cloud7.firstpage.R;
import com.cloud7.firstpage.VersionAndModel;
import com.cloud7.firstpage.ooperation.EditActivity;
import com.cloud7.firstpage.view.SmartImageView;

public class FragmentSinglePage extends Fragment implements OnClickListener {
	private SmartImageView iv;
	private VersionAndModel activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity = (VersionAndModel) getActivity();
		View view = inflater.inflate(R.layout.fragment_singlepage, container,
				false);
		iv = (SmartImageView) view.findViewById(R.id.iv);
		// iv.setImageUrl("http://pic10.nipic.com/20101005/2848978_120354009000_2.jpg");
		iv.setOnClickListener(this);
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onClick(View v) {
		startActivity(new Intent(activity, EditActivity.class));
	}
}
