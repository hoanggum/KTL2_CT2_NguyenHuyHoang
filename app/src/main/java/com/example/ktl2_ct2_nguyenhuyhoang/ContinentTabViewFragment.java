package com.example.ktl2_ct2_nguyenhuyhoang;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ktl2_ct2_nguyenhuyhoang.CustomAdapter.CustomCountryAdapter;
import com.example.ktl2_ct2_nguyenhuyhoang.Model.Country;
import com.example.ktl2_ct2_nguyenhuyhoang.databinding.FragmentContinentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContinentTabViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContinentTabViewFragment extends Fragment {

    private FragmentContinentBinding fragmentContinentBinding;
    private String continent;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContinentTabViewFragment() {
        // Required empty public constructor
        this.continent = "";
    }
    public ContinentTabViewFragment(String continent) {
        // Required empty public constructor
        this.continent = continent;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContinentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContinentTabViewFragment newInstance(String param1, String param2) {
        ContinentTabViewFragment fragment = new ContinentTabViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentContinentBinding = FragmentContinentBinding.inflate(getLayoutInflater());
        return fragmentContinentBinding.getRoot();
    }

    String url = "https://restcountries.com/v3.1/independent?status=true&fields=languages,capital,name,population,continents,flags";
    private ArrayList<Country> list;
    CustomCountryAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(continent != ""){
            init();
        } else {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
    private void init(){
        list = new ArrayList<>();
        fetchDataFromApi(getContext());
    }
    void fetchDataFromApi(Context context) {
        fragmentContinentBinding.textViewLoading.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJsonData(response, context);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Có lỗi xảy ra khi tải dữ liệu", Toast.LENGTH_LONG).show();
                fragmentContinentBinding.textViewLoading.setVisibility(View.GONE);
            }
        });
        // Thiết lập thời gian chờ và số lần thử lại
        int socketTimeout = 50000; // 30 giây
        request.setRetryPolicy(new DefaultRetryPolicy(
                socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Thêm yêu cầu vào hàng đợi
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }
    void parseJsonData(String jsonString, Context context) {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject countryJson = jsonArray.getJSONObject(i);
                JSONArray continentsArray = countryJson.getJSONArray("continents");
                if (continentsArray != null && continentsArray.length() > 0) {
                    String continentName = continentsArray.getString(0);
                    if (continent.equalsIgnoreCase(continentName)) {
                        // Lọc các quốc gia thuộc châu lục đã chỉ định
                        String commonName = countryJson.getJSONObject("name").getString("common");
                        String officialName = countryJson.getJSONObject("name").getString("official");
                        String capital = countryJson.getJSONArray("capital").getString(0);
                        int population = countryJson.getInt("population");
                        String language = countryJson.getJSONObject("languages").keys().next();
                        String flagUrl = countryJson.getJSONObject("flags").getString("png");

                        // Tạo đối tượng Country và thêm vào danh sách
                        Country country = new Country(flagUrl, commonName, officialName, capital, language, population, continentName);
                        list.add(country);
                    }
                }
            }
            adapter = new CustomCountryAdapter(context, R.layout.custom_country_item_listview, list);
            fragmentContinentBinding.listViewItems.setAdapter(adapter);
            fragmentContinentBinding.textViewLoading.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Có lỗi xảy ra khi xử lý dữ liệu JSON", Toast.LENGTH_LONG).show();
        }
    }
}