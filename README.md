# 소개팅 앱

## OverView

각각의 TAB에 대해 설명하기에 앞서 전체적인 구조를 먼저 설명하려고 한다. 기본적으로 MainActivity에서 Adapter를 통해 TAB을 구성하는 Fragment들을 관리하는 구조이다. 그리고 소개팅 기능의 경우 Fragment → Activity, Fragment → Fragment, Activity → Fragment, Activity → Activity로의 이동이 모두 이루어진다.  

## TAB1: Contact

### Description

- **연락처 불러오기**
- **연락처 추가**
- **연락처 검색**

### **연락처 불러오기**

연락처 정보는 기기 내부저장소에서 json 형식으로 저장했다. 

json 파일에서 FileinputStream 메소드로 문자열 정보를 받아온 뒤 Gson 라이브러리를 이용하여 ContactsVO라는 이름의 JAVA 객체를 변경한 데이터를 사용했다.

데이터는 ArrayList 형식으로 받아와 Recyclerview adapter로 전달했으며 그 결과는 아래와 같다.

![https://user-images.githubusercontent.com/96767498/148046081-89fbff0e-42be-4e7f-96ba-e926ecb91980.PNG](https://user-images.githubusercontent.com/96767498/148046081-89fbff0e-42be-4e7f-96ba-e926ecb91980.PNG)

기본적으로 연락처는 이름을 기준으로 정렬했다.

### **연락처 추가**

오른쪽 아래의 + 버튼을 클릭하면 연락처 정보를 추가할 수 있으며 Fragment Dialog를 이용해서 구현했다.

![https://user-images.githubusercontent.com/96767498/148046653-4960a840-05e3-4274-9a82-f23651bc3907.PNG](https://user-images.githubusercontent.com/96767498/148046653-4960a840-05e3-4274-9a82-f23651bc3907.PNG)

### **연락처 검색**

연락처 탭 가장 위 입력창을 클릭하면 이름을 기준으로 연락처를 검색할 수 있다. 예를 들어 검색 창에 "a" 를 입력하면 이름에 "a" 가 들어가는 모든 데이터를 보여주는 식이다.

구현은 TextWatcher 메소드를 사용했으며 검색 창에서 새로운 글자가 입력될 때마다 TextWatcher를 통해 search메소드가 실행되어 새로운 recyclerview를 화면에 출력하는 방식이다.

search 메소드는 아래와 같다.

```java
@RequiresApi(api = Build.VERSION_CODES.N)
  public void search(String charText) {

      arraylist = getContactList();
      // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
      list.clear();
    
      // 문자 입력을 할때..
      else {
          // 리스트의 모든 데이터를 검색한다.
          for (int i = 0; i < arraylist.size(); i++) {
              // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
              if (arraylist.get(i).name.contains(charText)) {
                  // 검색된 데이터를 리스트에 추가한다.
                  list.add(arraylist.get(i));
                  //textView2.setText("sf");
              }
          }
      }
      // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
      adapter.notifyDataSetChanged();
  }
```

## TAB2: Gallery

### Description

- **GridView 사용**
- **클릭시 새로운 이미지 창**
- **Shuffle 기능**

### GridView 사용

drawable 폴더에 저장되어 있는 image 파일들을 GridView를 활용해 화면에 아래처럼 보여준다.

<img src = "https://user-images.githubusercontent.com/81549602/148057991-d2c2f149-84cd-4eb6-bf7d-264300ac9bd7.png" width = "350" height = "700"/>

이 과정에서 ImageGridAdapter 파일이 Gridview에 알맞게 image data들을 관리하는 역할을 한다. ImageGridAdapter에서는 아래 코드와 같이 이미지데이터를 bitmap 형식으로 변환하여 위처럼 격자 형태로 이미지가 보이도록 구현했다.

```java
Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageIDs[position]);
bmp = Bitmap.createScaledBitmap(bmp, 320, 240, false);

imageView = new ImageView(context.getContext());
imageView.setAdjustViewBounds(true);

imageView.setImageBitmap(bmp);
```

### 클릭 시 새로운 이미지 창

갤러리에 있는 이미지를 클릭할 시 아래처럼 이미지가 확대되어 보이도록 구현했다. 

이를 위해 ImageActivity에서 아래 코드와 같이 iamgeView를 통해 이미지를 보여준다.

```java
ImageView imageView = (ImageView)findViewById(R.id.imageView);
setImage(imageView);
```
<img src = "https://user-images.githubusercontent.com/81549602/148060574-c25e7abf-d722-489f-9630-7ec19c8dca3a.png" width = "350" height = "700"/>

### Shuffle 기능

갤러리 탭 화면에 보면 오른쪽 아래에 버튼이 있다. 이 버튼을 클릭하면 갤러리 이미지들이 랜덤하게 셔플되어 화면에 나타난다. 

좌측 사진이 원래 갤러리 모습이었지만 셔플 버튼을 클릭 후, 우측 사진처럼 변했다.

<img src = "https://user-images.githubusercontent.com/81549602/148060711-34c2281c-636f-4b48-bd87-38e86dbcf894.png" width = "350" height = "700"/>
<img src = "https://user-images.githubusercontent.com/81549602/148060744-7c2c5fd7-ef74-4c81-9abb-9e3061271d8c.png" width = "350" height = "700"/>

버튼을 클릭했을 때, onClick 이벤트가 발생하여 image들이 셔플되고, 새롭게 어댑터를 실행하여 화면에 나타나도록 아래처럼 코드를 작성했다.

```java
shuffle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageIDs = shuffleArray(imageIDs);
                GridView gridViewImages = (GridView)viewgroup.findViewById(R.id.gridView);
                ImageGridAdapter imageGridAdapter = new ImageGridAdapter(inflater, container, cont, imageIDs);
                gridViewImages.setAdapter(imageGridAdapter);
            }
        });
```

## TAB3: BlindDate

### Description

- **로그인**
- **회원가입**
- **설문**
- **매칭 알고리즘**
- **맞춤형 추천**

### 로그인

로그인 기능을 사용하기 위해서는 데이터베이스가 필요했고, 우리는 SQLite를 사용해서 데이터베이스를 구현했다. 

아래 화면에서 사용자가 기존에 회원가입을 했다면 이름, 전화번호, 비밀번호를 입력한 뒤 로그인을 한다. 만약 회원가입한 적이 없다면 회원가입 버튼을 눌러 회원가입하는 화면으로 이동한다.

<img src = "https://user-images.githubusercontent.com/81549602/148060819-c1c8934d-26f2-4fbf-a37e-d38510933630.png" width = "350" height = "700"/>

이 과정에서 기존 사용자라면 데이터베이스에서 데이터를 가져와 사용자가 입력한 이름, 전화번호, 비밀번호를 비교하여 데이터베이스에 존재하는 값과 동일한 경우, 로그인이 되도록 구현했다. 또한, 만약 값이 입력되지 않은 경우, 값을 입력해달라는 alert문구를 띄웠다.

```java
login_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        user_name = login_name.getText().toString();
        user_password = login_password.getText().toString();
        user_number = login_number.getText().toString();

        if(user_name.getBytes().length <= 0 || user_number.getBytes().length <= 0 || user_password.getBytes().length <= 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setTitle("알림창").setMessage("값을 입력해주세요");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        else {
            DBHelper helper = new DBHelper(requireActivity(), "FirstProject.db", null, 1);
            SQLiteDatabase db = helper.getWritableDatabase();
            String sql = "select * from login where number = '" + user_number + "'";
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            if(cursor.getCount() != 0) {

                if (cursor.getString(1).equals(user_name) && cursor.getString(2).equals(user_password)) {
```

### 회원가입

아래와 같은 회원가입 화면에서 이름, 전화번호, 비밀번호, 성별을 입력하여 회원가입을 진행한다. 

<img src = "https://user-images.githubusercontent.com/81549602/148060836-c38bfb6c-1d44-450a-813c-d7329954aa4b.png" width = "350" height = "700"/>

가입버튼을 누르면 아래 코드와 같이 데이터베이스에 새로운 데이터를 insert해준다.

```java
DBHelper helper = new DBHelper(JoinActivity.this, "FirstProject.db", null, 1);
SQLiteDatabase db = helper.getWritableDatabase();
ContentValues values = new ContentValues();
values.put("gender", user_gender);
values.put("password", user_password);
values.put("name", user_name);
values.put("number", user_number);
db.insert("login",null, values);
```

### 설문

회원가입이 완료되면 아래처럼 설문이 진행된다.

<img src = "https://user-images.githubusercontent.com/81549602/148060848-854bc6fd-25f8-42ac-b3f3-b6abbc7bc509.png" width = "350" height = "700"/>

<img src = "https://user-images.githubusercontent.com/81549602/148060861-6a9af024-f5fe-462a-b380-1a23b1dd9f36.png" width = "350" height = "700"/>

설문 질문은 총 10개인데 이는 모두 Fragment로 구성되어 있으며 Fragment에서 Fragment로 이동하면서 설문이 진행된다. 이를 구현하기 위해 Activity에서 다수의 Fragment를 생성해서 관리한다.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.join_main);
    frameQ1 = new Q1Fragment();
    frameQ2 = new Q2Fragment();
    frameQ3 = new Q3Fragment();
    frameQ4 = new Q4Fragment();
    frameQ5 = new Q5Fragment();
    frameQ6 = new Q6Fragment();
    frameQ7 = new Q7Fragment();
    frameQ8 = new Q8Fragment();
    frameQ9 = new Q9Fragment();
    frameQ10 = new Q10Fragment();

    Intent intent = getIntent();
    user_gender = intent.getExtras().getString("user_gender");
    user_password = intent.getExtras().getString("user_password");
    user_name = intent.getExtras().getString("user_name");
    user_number = intent.getExtras().getString("user_number");

    fragmentManager = getSupportFragmentManager();
    transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.frame_layout, frameQ1).commit();
```

### 매칭 알고리즘

설문 결과와 데이터베이스의 정보를 비교해 최적의 상대를 매칭해준다. 이 과정에서 RMSE(Root Mean Square Error)가 최소가 되는 상대를 찾는다. 그리고 받은 MBTI 데이터도 고려하여  상대를 매칭한다.

```java
private double RMSE_algorithm(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9, String x10){
    double result = Math.sqrt((Math.pow(x1-q1,2)*0.99 + Math.pow(x2-q2,2)*0.98 + Math.pow(x3-q3, 2)*0.97 + Math.pow(x4-q4,2)*1.01 + Math.pow(x5-q5,2)*1.02 + Math.pow(x6-q6, 2)*1.03 + Math.pow(x7-q7,2)*1.04 + Math.pow(x8-q8,2)*0.96 + Math.pow(x9-q9, 2))*0.95/9);
    return result;
}
```

### 맞춤형 추천

설문이 완료되거나, 로그인에 성공하면 아래 화면처럼 알고리즘을 통해 매칭된 최적의 상대를 3명 추천해준다.

<img src = "https://user-images.githubusercontent.com/81549602/148060882-8a069a9d-191a-4672-8284-ac1771f4b22e.jpeg" width = "350" height = "700"/>
