package com.relateddigital.shoppingdemo.tabs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.relateddigital.shoppingdem.R;
import com.relateddigital.shoppingdem.databinding.FragmentBasketBinding;
import com.relateddigital.shoppingdemo.Utils;
import com.relateddigital.shoppingdemo.fragments.ShippingFragment;
import com.squareup.picasso.Picasso;
import com.visilabs.Visilabs;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class BasketFragment extends Fragment {

    FragmentBasketBinding mBinding;

    String TAG = "Basketragment";
    String NAME = "Shopping";

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false);

        mBinding.tvProductBrand.setText("Kozmo");
        mBinding.tvProductName.setText("Nectarine Blossom & Honey Body & Hand Lotion");
        Picasso.get().load("http://kodblogu.net/one2one/1.jpg").into(mBinding.ivProduct);

        HashMap<String, String> parameters= new HashMap<>();
        parameters.put("OM.pbid", String.valueOf(UUID.randomUUID()));
        parameters.put("OM.pb", "77");
        parameters.put("OM.pu","2");
        parameters.put("OM.ppr","78");
        Visilabs.CallAPI().customEvent("Cart", parameters);

        mBinding.btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                payAction();

                HashMap<String, String> parameters= new HashMap<>();
                parameters.put("OM.tid","1234568");
                parameters.put("OM.pb", "77");
                parameters.put("OM.pu","2");
                parameters.put("OM.ppr","78");
                Visilabs.CallAPI().customEvent("Product Purchase", parameters);

            }
        });

        Log.i(NAME, TAG);

        return mBinding.getRoot();
    }

    private void payAction() {

        Fragment shippingFragment = new ShippingFragment();
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.container, shippingFragment);
        ft.commit();
    }
}
