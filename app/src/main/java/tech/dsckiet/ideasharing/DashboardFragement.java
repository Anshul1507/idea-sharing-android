package tech.dsckiet.ideasharing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static tech.dsckiet.ideasharing.Config.BASE_URL;
import static tech.dsckiet.ideasharing.Config.SHOW_IDEAS_API;

public class DashboardFragement extends Fragment {
    private String TAG = DashboardFragement.class.getSimpleName();

    private FirebaseAuth mAuth;


    private static String GET_URL = BASE_URL + SHOW_IDEAS_API;

    private RecyclerView recyclerViewIdeas;
    private IdeasAdapter mAdapter;
    private FloatingActionButton fab;
    private JSONObject jsonObj;
    private JSONArray jsonArray;
    private String jsonStr;
    private RequestQueue mQueue;
    final ArrayList<IdeasRecyclerView> mAdapterList = new ArrayList<>();
//    ArrayList<String> titleList = new ArrayList<>();
//    ArrayList<String> descList = new ArrayList<>();
//    ArrayList<String> techList = new ArrayList<>();
//    private RecyclerView.Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        //adding user mail-id after api
//        GET_URL += user.getEmail();

        fab = view.findViewById(R.id.floating_addBtn);
        recyclerViewIdeas = view.findViewById(R.id.recycler_view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddIdeaActivity.class));
            }
        });

//        new GetIdeas().execute();
        loadData();


//        recyclerViewIdeas.setHasFixedSize(true);
//        recyclerViewIdeas.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerViewIdeas.setAdapter(mAdapter);

        return view;
    }

    private void loadData() {
  mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GET_URL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                                try {
//                                    jsonObj = new JSONObject();

                                    jsonArray = response.getJSONArray("ideas");


                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject attendeeObj = jsonArray.getJSONObject(i);

                                        String titleGET = attendeeObj.getString("title");
                                        String descGET = attendeeObj.getString("desc");
                                        mAdapterList.add(new IdeasRecyclerView(titleGET,descGET));
//                        titleList.add(attendeeObj.getString("tile"));
//                        descList.add(attendeeObj.getString("desc"));
//                        techList.add(attendeeObj.getString("technology"));
                                    }
//                    Toast.makeText(getContext(), "Background", Toast.LENGTH_SHORT).show();
                                    mAdapter = new IdeasAdapter(getContext(),mAdapterList);

                                    recyclerViewIdeas.setHasFixedSize(true);
                                    final LinearLayoutManager layoutManager = new LinearLayoutManager(DashboardFragement.this.getActivity());
                                    layoutManager.setOrientation(RecyclerView.VERTICAL);
                                    recyclerViewIdeas.setLayoutManager(layoutManager);
                                    recyclerViewIdeas.setItemAnimator(new DefaultItemAnimator());
                                    recyclerViewIdeas.setAdapter(mAdapter);
                                    Log.e(TAG, "onResponse: BackGround" );

                                } catch (final JSONException e) {
                                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getContext(),
                                                    "Json parsing error: " + e.getMessage(),
                                                    Toast.LENGTH_LONG)
                                                    .show();
                                        }
                                    };

                                }

//                            } else {
//                                Log.e(TAG, "Couldn't get json from server.");
//                                new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(getContext(), "Error Loading data!!\nTry Again.", Toast.LENGTH_LONG).show();
//                                    }
//                                };
//                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            mQueue.add(request);
    }

//    public void sendBundle() {
//        adapter = new IdeasAdapter(getContext(), titleList, descList, techList);
//        recyclerView.setAdapter(adapter);
//    }

//    private class GetIdeas extends AsyncTask<Void, Void, Void> {
//        private final ProgressDialog progressDialog = new ProgressDialog(getContext());
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog.setMessage("Loading Ideas");
//            progressDialog.setCancelable(false);
//            progressDialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            HttpHandler sh = new HttpHandler();
//
//            jsonStr = sh.makeServiceCall(GET_URL);
//            mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GET_URL, null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            if (jsonStr != null) {
//                                try {
//                                    jsonObj = new JSONObject(jsonStr);
//
//                                    jsonArray = jsonObj.getJSONArray("ideas");
//
//
//                                    for (int i = 0; i < jsonArray.length(); i++) {
//                                        JSONObject attendeeObj = jsonArray.getJSONObject(i);
//
//                                        String titleGET = attendeeObj.getString("title");
//                                        String descGET = attendeeObj.getString("desc");
//                                        mAdapterList.add(new IdeasRecyclerView(titleGET,descGET));
////                        titleList.add(attendeeObj.getString("tile"));
////                        descList.add(attendeeObj.getString("desc"));
////                        techList.add(attendeeObj.getString("technology"));
//                                    }
////                    Toast.makeText(getContext(), "Background", Toast.LENGTH_SHORT).show();
//                                    mAdapter = new IdeasAdapter(getContext(),mAdapterList);
//                                    recyclerViewIdeas.setAdapter(mAdapter);
//
//                                } catch (final JSONException e) {
//                                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                                    new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Toast.makeText(getContext(),
//                                                    "Json parsing error: " + e.getMessage(),
//                                                    Toast.LENGTH_LONG)
//                                                    .show();
//                                        }
//                                    };
//
//                                }
//
//                            } else {
//                                Log.e(TAG, "Couldn't get json from server.");
//                                new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(getContext(), "Error Loading data!!\nTry Again.", Toast.LENGTH_LONG).show();
//                                    }
//                                };
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    });
//            mQueue.add(request);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            progressDialog.dismiss();
//
////            Toast.makeText(getContext(), "Post Execute", Toast.LENGTH_SHORT).show();
//
//        }
//    }

//    private void runOnUiThread(Runnable runnable) {
//    }
}
