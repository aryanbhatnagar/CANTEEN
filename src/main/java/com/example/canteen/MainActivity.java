package com.example.canteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    String username,userId;
    String Lusername;
    String password;
    String Lpassword;
    String phone;
    String regno;
    String name;
    int TOTAL=0;
    int c[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    FirebaseAuth fAuth=FirebaseAuth.getInstance();
    FirebaseFirestore fstore= FirebaseFirestore.getInstance();
    ArrayList<String> cartItems= new ArrayList<String>();



    String[] ITEMS={"TEA @ 15/-","COFFEE @ 25/-","JUICE @ 20/-", "COLD DRINK @ 35/-",
            "CHIPS @ 20/-","NACHOS @ 45/-", "POPCORN @ 30/-","COOKIES @ 15/-",
            "BURGER @ 35/-","PIZZA @ 85/-", "MAGGI @ 30/-", "ROLL @ 45/-",
            "PASTRY @ 25/-","DONNUT @ 40/-","ICE CREAM @ 25/-","CHOCOLATE @ 20/-"};



    public void register(View view)
    {
        setContentView(R.layout.register_main);
    }

    public void Log(View view)
    {
        setContentView(R.layout.activity_main);
    }

    public void submit(View view)
    {
        final ProgressBar progressBar2 = findViewById(R.id.progressBar2);
        username=((EditText)findViewById(R.id.editTextTextPersonName4)).getText().toString();
        password=((EditText)findViewById(R.id.editTextTextPersonName5)).getText().toString();
        name=((EditText)findViewById(R.id.editTextTextPersonName2)).getText().toString();
        phone=((EditText)findViewById(R.id.editTextTextPersonName6)).getText().toString();
        regno=((EditText)findViewById(R.id.editTextTextPersonName3)).getText().toString();

        if(TextUtils.isEmpty(username)) {
            ((EditText) findViewById(R.id.editTextTextPersonName4)).setError("Email is Required.");
            return;
        }
        if(TextUtils.isEmpty(name) ){
            ((EditText)findViewById(R.id.editTextTextPersonName2)).setError("Please enter Name");
            return;
        }
        if(TextUtils.isEmpty(phone)) {
            ((EditText) findViewById(R.id.editTextTextPersonName6)).setError("Please enter Phone no.");
            return;
        }
        if(TextUtils.isEmpty(regno)) {
            ((EditText) findViewById(R.id.editTextTextPersonName3)).setError("Please enter Reg No.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ((EditText) findViewById(R.id.editTextTextPersonName5)).setError("Password is Required");
            return;
        }

        if (password.length() < 6) {
            ((EditText) findViewById(R.id.editTextTextPersonName5)).setError("Password must be more than 6 Character!");
            return;
        }
        progressBar2.setVisibility(View.VISIBLE);


        fAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    userId=fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference=fstore.collection(userId).document("USER DETAILS");
                    Map<String,Object> user=new HashMap<>();
                    user.put("NAME",name);
                    user.put("Phone",phone);
                    user.put("Reg No.: ",regno);
                    user.put("Email",username);
                    documentReference.set(user);
                    Toast.makeText(getApplicationContext() ,"Registered Successfully",Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.activity_main);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"ERROR : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    progressBar2.setVisibility(View.GONE);
                }
            }
        });

       /* database =FirebaseDatabase.getInstance();
        myRef =database.getReference();

        helperClass help = new helperClass(name,regno,username,password,phone);
        myRef.child(password).setValue(help);*/

// Toast.makeText(this,"REGISTERED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
       // setContentView(R.layout.activity_main);
    }

    public void successful(View view)
    {
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        Lusername=((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString();
        Lpassword=((EditText)findViewById(R.id.editTextTextPassword)).getText().toString();
       // if (myRef.getKey().equals(Lpassword))

        if(TextUtils.isEmpty(Lusername)) {
            ((EditText)findViewById(R.id.editTextTextPersonName)).setError("Email is Required.");
            return;
        }

        if (TextUtils.isEmpty(Lpassword)) {
            ((EditText)findViewById(R.id.editTextTextPassword)).setError("Password is Required");
            return;
        }

        if (Lpassword.length() < 6) {
            ((EditText)findViewById(R.id.editTextTextPassword)).setError("Password must be more than 6 Character!");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        fAuth.signInWithEmailAndPassword(Lusername,Lpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Toast.makeText(getApplicationContext() ,"Logged in Successfully",Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.mainscreen);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"ERROR : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
       /* if(Lusername.equals(username) && Lpassword.equals(password))
        {
            setContentView(R.layout.mainscreen);
        }
        else
        {
          Toast.makeText(this,"INVALID CREDENTIALS",Toast.LENGTH_SHORT).show();
        }*/
    }
    public void beverages(View view)
    {
        setContentView(R.layout.beverages);
    }
    public void fastfood(View view)
    {
        setContentView(R.layout.fastfood);
    }
    public void snacks(View view)
    {
        setContentView(R.layout.snacks);
    }
    public void Sweet(View view)
    {
        setContentView(R.layout.sweets);
    }
    public void rEturn(View view)
    {
        setContentView(R.layout.mainscreen);
    }

    public void AddClick(View view) {
        switch (view.getId()) {
            case R.id.teaAdd:
                //cartItems.add(ITEMS[0]);
                TOTAL+=15;
                c[0]++;
                Toast.makeText(this,"1 Tea Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.coffeeAdd:
                //cartItems.add(ITEMS[1]);
                TOTAL+=25;
                c[1]++;
                Toast.makeText(this,"1 Coffee Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.juiceAdd:
                //cartItems.add(ITEMS[2]);
                TOTAL+=20;
                c[2]++;
                Toast.makeText(this,"1 Juice Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.coldDrinkAdd:
               // cartItems.add(ITEMS[3]);
                TOTAL+=35;
                c[3]++;
                Toast.makeText(this,"1 Cold Drink Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.chipsAdd:
                //cartItems.add(ITEMS[4]);
                TOTAL+=20;
                c[4]++;
                Toast.makeText(this,"1 Chips Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nachoAdd:
                //cartItems.add(ITEMS[5]);
                TOTAL+=45;
                c[5]++;
                Toast.makeText(this,"1 Nachos Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.popCornAdd:
                //cartItems.add(ITEMS[6]);
                TOTAL+=30;
                c[6]++;
                Toast.makeText(this,"1 PopCorn Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cookieAdd:
                //cartItems.add(ITEMS[7]);
                TOTAL+=15;
                c[7]++;
                Toast.makeText(this,"1 Cookie Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.burgerAdd:
                //cartItems.add(ITEMS[8]);
                TOTAL+=35;
                c[8]++;
                Toast.makeText(this,"1 Burger Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.pizzaAdd:
                //cartItems.add(ITEMS[9]);
                TOTAL+=85;
                c[9]++;
                Toast.makeText(this,"1 Pizza Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.maggiAdd:
                //cartItems.add(ITEMS[10]);
                TOTAL+=30;
                c[10]++;
                Toast.makeText(this,"1 Maggi Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rollAdd:
                //cartItems.add(ITEMS[11]);
                TOTAL+=45;
                c[11]++;
                Toast.makeText(this,"1 Roll Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.pastryAdd:
                //cartItems.add(ITEMS[13]);
                TOTAL+=25;
                c[12]++;
                Toast.makeText(this,"1 Pastry Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.donnutAdd:
                //cartItems.add(ITEMS[14]);
                TOTAL+=40;
                c[13]++;
                Toast.makeText(this,"1 Donnut Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iceCreamAdd:
                //cartItems.add(ITEMS[12]);
                TOTAL+=25;
                c[14]++;
                Toast.makeText(this,"1 Ice Cream Added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.chocolateAdd:
                //cartItems.add(ITEMS[15]);
                TOTAL+=20;
                c[15]++;
                Toast.makeText(this,"1 Chocolate Added",Toast.LENGTH_SHORT).show();
                break;

        }
    }
        public void RemClick(View view)
        {
            switch (view.getId())
            {
                case R.id.teaRem:
                    if(c[0]!=0) {
                        c[0]--;
                        TOTAL -= 15;
                        Toast.makeText(this,"1 Tea Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Tea in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.coffeeRem:
                    if(c[1]!=0) {
                        c[1]--;
                        TOTAL -= 25;
                        Toast.makeText(this,"1 Coffee Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Coffee in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.juiceRem:
                    if(c[2]!=0) {
                        c[2]--;
                        TOTAL -= 20;
                        Toast.makeText(this,"1 Juice Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Juice in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.coldDrinkRem:
                    if(c[3]!=0) {
                        c[3]--;
                        TOTAL -= 35;
                        Toast.makeText(this,"1 Cold Drink Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Cold Drink in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.chipsRem:
                    if(c[4]!=0) {
                        c[4]--;
                        TOTAL -= 20;
                        Toast.makeText(this,"1 Chips Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Chips in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.navhoRem:
                    if(c[5]!=0) {
                        c[5]--;
                        TOTAL -= 45;
                        Toast.makeText(this,"1 Nachos Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Nachos in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.popCornRem:
                    if(c[6]!=0) {
                        c[6]--;
                        TOTAL -= 30;
                        Toast.makeText(this,"1 PopCorn Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No PopCorn in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.cookieRem:
                    if(c[7]!=0) {
                        c[7]--;
                        TOTAL -= 15;
                        Toast.makeText(this,"1 Cookie Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Cookie in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.burgerRem:
                    if(c[8]!=0) {
                        c[8]--;
                        TOTAL -= 35;
                        Toast.makeText(this,"1 Burger Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Burger in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.pizzaRem:
                    if(c[9]!=0) {
                        c[9]--;
                        TOTAL -= 85;
                        Toast.makeText(this,"1 Pizza Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Pizza in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.maggiRem:
                    if(c[10]!=0) {
                        c[10]--;
                        TOTAL -= 30;
                        Toast.makeText(this,"1 Maggi Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Maggi in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.rollRem:
                    if(c[11]!=0) {
                        c[11]--;
                        TOTAL -= 45;
                        Toast.makeText(this,"1 Roll Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Roll in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.pastryRem:
                    if(c[12]!=0) {
                        c[12]--;
                        TOTAL -= 25;
                        Toast.makeText(this,"1 Pastry Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Pastry in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.donnutRem:
                    if(c[13]!=0) {
                        c[13]--;
                        TOTAL -= 40;
                        Toast.makeText(this,"1 Donut Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Donut in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.iceCreamRem:
                    if(c[14]!=0) {
                        c[14]--;
                        TOTAL -= 25;
                        Toast.makeText(this,"1 Ice Cream Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Ice Cream in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.chocolateRem:
                    if(c[15]!=0) {
                        c[15]--;
                        TOTAL -= 20;
                        Toast.makeText(this,"1 Chocolate Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this,"No Chocolate in Cart",Toast.LENGTH_SHORT).show();
                    }
                    break;

            }

    }
    public void cart(View view)
    {
        cartItems.clear();
        setContentView(R.layout.mycart);
        if(TOTAL>0) {
            int i = 0;
            for (i = 0; i < 16; i++) {
                if (c[i] > 0) {
                    cartItems.add(c[i] + " x " + ITEMS[i]);
                }
            }
            cartItems.add("    ");
            cartItems.add("GRAND TOTAL =\t\t" + TOTAL + "/-");
        }
        else
        {
            cartItems.add("Nothing Added to Cart");
        }
            ListView mylistview = (ListView) findViewById(R.id.mylistview);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cartItems);
            mylistview.setAdapter(arrayAdapter);

    }
    public void book_another(View view)
    {
        int i=0;
        TOTAL=0;
        for (i=0;i<16;i++)
        {
            c[i]=0;
        }
        setContentView(R.layout.mainscreen);
    }
    public void Confirm(View view) {
        if (TOTAL == 0) {
            Toast.makeText(this, "Nothing to Order\nPlease add items before Ordering", Toast.LENGTH_SHORT).show();
        }
        else {
            /*database =FirebaseDatabase.getInstance();
            myRef =database.getReference();
            myRef.child("ORDER : \n").setValue(cartItems);*/
            userId=fAuth.getCurrentUser().getUid();
            Date time= Calendar.getInstance().getTime();
            String time1=time.toString();
            DocumentReference documentReference=fstore.collection(userId).document("ORDERS").collection(time1).document();
            Map<String,Object> cart =new HashMap<>();
            int l=cartItems.size();
            String str=" ";
            for(int i=0;i<l-2;i++)
            {

                str+=cartItems.get(i)+"\n";
                cart.put("Item "+i+" = ",cartItems.get(i));
            }
            cart.put("Total",cartItems.get(l-1));
            documentReference.set(cart);
            str+=cartItems.get(l-2)+"\n";
            setContentView(R.layout.thankyouscreen);
        }
    }
    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        setContentView(R.layout.activity_main);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main);
        if(fAuth.getCurrentUser()!=null)
        {
            setContentView(R.layout.mainscreen);
        }
    }
}