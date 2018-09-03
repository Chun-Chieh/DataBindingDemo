package com.chunchiehliang.networktest.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chunchiehliang.networktest.R;
import com.chunchiehliang.networktest.databinding.SheetFilterBinding;

/**
 * @author Chun-Chieh Liang on 7/28/18.
 */
public class FilterBottomSheet extends BottomSheetDialogFragment {
    private SheetFilterBinding mBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.sheet_filter, container, false);

        mBinding.setHandler(new BottomSheetOnClickHandler());

        return mBinding.getRoot();
    }

    public class BottomSheetOnClickHandler{
        public void onTextClick(View view){
            switch (view.getId()){
                case R.id.text_filter_time:
                    Toast.makeText(getContext(), "Time", Toast.LENGTH_SHORT).show();
                    dismiss();
                    break;
                case R.id.text_filter_mag:
                    Toast.makeText(getContext(), "Mag", Toast.LENGTH_SHORT).show();
                    dismiss();
                    break;
            }
        }
    }
}
