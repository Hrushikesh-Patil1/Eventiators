package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class OrderCake extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cake);
        GridLayout layout = (GridLayout) findViewById(R.id.order_cake_layout);
        setsingleEvent(layout);
    }


    private void setsingleEvent(GridLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {

            CardView cv = (CardView) layout.getChildAt(i);
            final int finalIntI = i;
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalIntI == 0) {
                        Intent j = new Intent(OrderCake.this, OrderCakeByIntent.class);
                        j.putExtra("resId", R.drawable.assortedfruitandalmondcake);
                        j.putExtra("cake_name","Assorted Fruit and Almond Cake");
                        j.putExtra("cake_code","AFAAC60");
                        j.putExtra("cake_dis","Freshly baked Vanilla cake, Whipped cream and bountiful tropical fruits come together to create a treat that is even tastier than it looks. This combination of whipped cream and fresh fruit is a perfect start for any celebration and an enticing end to any meal.");
                        j.putExtra("cake_price","600 Rs./0.5 KG");
                        startActivity(j);

                    } else if (finalIntI == 1) {
                        Intent j = new Intent(OrderCake.this,OrderCakeByIntent.class );
                        j.putExtra("resId", R.drawable.butterscotchchocolatecake);
                        j.putExtra("cake_name","Butterscotch Chocolate Cake");
                        j.putExtra("cake_code","BCC50");
                        j.putExtra("cake_dis","This lovely crunch cake is one enticing sweet course! Prepared with three cream-filled layers of mushy cake and a chocolaty creamy layer in the middle, along with appetizing rich cream chocolate glaze frosting and butterscotch nougat, this cake is a good way to sum up your celebrations!");
                        j.putExtra("cake_price","500 Rs./0.5 KG");
                        startActivity(j);
                    }
                    else if (finalIntI == 2) {
                        Intent j = new Intent(OrderCake.this,OrderCakeByIntent.class  );
                        j.putExtra("resId", R.drawable.halfchocolatehalfvanillacake);
                        j.putExtra("cake_name","Half Chocolate Half Vanilla Cake");
                        j.putExtra("cake_code","HCHVC50");
                        j.putExtra("cake_dis","A toothsome treat for the sugar lovers who are just passionate about chocolate and relish the mushiness of cake. Made of two layers of impossibly moist chocolate filled with mushy rich cream, this cake carries the enticing aroma of Vanilla and sumptuousness of chocolate. On top of it, the cake is garnished with handcrafted white and brown chocolate. ");
                        j.putExtra("cake_price","500 Rs./0.5 KG");
                        startActivity(j);
                    }
                    else if (finalIntI == 3) {
                        Intent j = new Intent(OrderCake.this,OrderCakeByIntent.class );
                        j.putExtra("resId", R.drawable.heartshaperedvelvetcake);
                        j.putExtra("cake_name","Heart Shape Red Velvet Cake");
                        j.putExtra("cake_code","HSRVC70");
                        j.putExtra("cake_dis","The deliciousness of red velvet has been baked into the shape of a heart that resembles your sheer love and affection for your sweetheart. Tender, moist, and fluffy in texture, this captivating red velvet cake is a perfect sweet to cast the spell of love.");
                        j.putExtra("cake_price","700 Rs./0.5 KG");
                        startActivity(j);
                    }
                    else if (finalIntI == 4) {
                        Intent j = new Intent(OrderCake.this,OrderCakeByIntent.class );
                        j.putExtra("resId", R.drawable.heavenlyredvelvetchocolatecake);
                        j.putExtra("cake_name","Heavenly Red Velvet Chocolate Cake");
                        j.putExtra("cake_code","HRVCC60");
                        j.putExtra("cake_dis","The two celebratory flavours in a single cake--red velvet cake layers entirely covered in dark chocolate cream roses sprinkled with red velvet crumbs. A slice of the cake won't be enough to celebrate the fusion of flavours; you would need a whole cake.");
                        j.putExtra("cake_price","600 Rs./0.5 KG");
                        startActivity(j);
                    }
                    else if (finalIntI == 5) {
                        Intent j = new Intent(OrderCake.this,OrderCakeByIntent.class );
                        j.putExtra("resId", R.drawable.mangopulpcake);
                        j.putExtra("cake_name","Mango Pulp Cake");
                        j.putExtra("cake_code","MPC52");
                        j.putExtra("cake_dis","This cake is called HAPPY! With its fine flavours and bright texture, you cannot help but adorn a gleaming smile on your face. Crowned with white chocolate, this cake with mushy base is finely lathered with mango glaze on the top and crammed with almond nuggets on the sides. Treat yourself with this treat infused with sunshine");
                        j.putExtra("cake_price","525 Rs./0.5 KG");
                        startActivity(j);
                    }

                    else if (finalIntI == 6) {
                        Intent j = new Intent(OrderCake.this,OrderCakeByIntent.class  );
                        j.putExtra("resId", R.drawable.pineapplecreamcake);
                        j.putExtra("cake_name","Pineapple Cream Cake");
                        j.putExtra("cake_code","PCC40");
                        j.putExtra("cake_dis","This cake is meant for all celebratory occasions! This three layered delicious pineapple flavoured cake filled with intensely whipped rich cream and pineapple fillings, perfectly defines simplicity at its best. Topped with pineapple slices, chocolate flakes and a further adorned with cherries, this cake is the perfect blend of sweetness and fluffiness");
                        j.putExtra("cake_price","400 Rs./0.5 KG");
                        startActivity(j);
                    }

                    else if (finalIntI == 7) {
                        Intent j = new Intent(OrderCake.this,OrderCakeByIntent.class  );
                        j.putExtra("resId", R.drawable.redvelvetfruitlayercake);
                        j.putExtra("cake_name","Red Velvet Fruit Layer Cake");
                        j.putExtra("cake_code","RVFLC70");
                        j.putExtra("cake_dis","An exceptionally creamy Open-layered Red Velvet cake with a topping that tastes like a MASH UP between the tropical fruits is truly a luxurious treat. Experience the richness and smoothness in this delicacy which is laden with kiwis, cherries, apples, dragon fruits and black grapes. ");
                        j.putExtra("cake_price","700 Rs./0.5 KG");
                        startActivity(j);
                    }
                    else if (finalIntI == 8) {
                        Intent j = new Intent(OrderCake.this,OrderCakeByIntent.class );
                        j.putExtra("resId", R.drawable.roundchocolatetrufflecake);
                        j.putExtra("cake_name","Round Chocolate Truffle Cake");
                        j.putExtra("cake_code","RCTC50");
                        j.putExtra("cake_dis","The heavenly taste of this Choco Truffle Cake will even leave the hardest-to-please chocolate addicts tranquil in every sense. Prepared with three layers of relishing chocolate cream filled in between the soft stacks of chocolate cake, and adorned with beautiful chocolate icing and rich glaze chocolate coating, this one is just irresistible.");
                        j.putExtra("cake_price","500 Rs./0.5 KG");
                        startActivity(j);
                    }

                    else if (finalIntI == 9) {
                        Intent j = new Intent(OrderCake.this,OrderCakeByIntent.class );
                        j.putExtra("resId", R.drawable.roundshapebutterscotchcake);
                        j.putExtra("cake_name","Round Shape Butterscotch Cake");
                        j.putExtra("cake_code","RSBC43");
                        j.putExtra("cake_dis","The heavenly taste of this Choco Truffle Cake will even leave the hardest-to-please chocolate addicts tranquil in every sense. Prepared with three layers of relishing chocolate cream filled in between the soft stacks of chocolate cake, and adorned with beautiful chocolate icing and rich glaze chocolate coating, this one is just irresistible.");
                        j.putExtra("cake_price","430 Rs./0.5 KG");
                        startActivity(j);
                    }

                }
            });

        }
    }
}