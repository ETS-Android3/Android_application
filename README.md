# Tab1: 연락처
  ## Features:
    1. 연락처 불러오기
    2. 연락처 추가
    3. 연락처 검색
    
### 1. 연락처 불러오기
######
  ###### 연락처 정보는 기기 내부 저장소에 json 형식으로 저장하였습니다.
  ###### json 파일에서 FileInputStream 메소드로 문자열 정보를 받아 온 뒤 Gson 라이브러리를 이용하여 ContactsVO 라는 이름의 JAVA 객체로 변경한 데이터를 사용하였습니다.  
  ###### 위 기능은 FirstTab.java 내의 getContactList 에 구현되어 있습니다.
  ###### 데이터는 ArrayList 형식으로 Recyclerview adapter 로 전달하였습니다.
```
  private ArrayList<ContactsVO> list = new ArrayList<>();
  list = getContactList();
  adapter = new FirstTabAdapter(getActivity(), list);
```
  
  ##### Recyclerview
  ![capture1](https://user-images.githubusercontent.com/96767498/148046081-89fbff0e-42be-4e7f-96ba-e926ecb91980.PNG)
  ###### 연락처는 이름을 기준으로 사전순 정렬시켰습니다.
  ######
### 2. 연락처 추가
  ######
  ###### 오른쪽 아래의 + 버튼을 클릭하면 연락처 정보를 추가할 수 있습니다.
  ###### Fragment Dialog를 이용하여 구현하였습니다.
  ![cpature2](https://user-images.githubusercontent.com/96767498/148046653-4960a840-05e3-4274-9a82-f23651bc3907.PNG)
  ###### Name 에 이름을 입력하고 Numer 에 연락처를 입력합니다.
  ######
### 3. 연락처 검색
  ######
  ###### 연락처 탭 가장 위 입력창을 클릭하면 이름을 기준으로 연락처를 검색할 수 있습니다.
  ###### 예를 들어 검색 창에 "a" 를 입력하면 이름에 "a" 가 들어가는 모든 데이터를 보여주는 식입니다.
  ###### 구현은 TextWatcher 메소드를 이용하였습니다.
  ###### 검색 창에서 새로운 글자가 입력될 때 마다 TextWatcher 을 통해 search 메소드가 실행되어 새로운 recyclerview를 화면에 출력하는 방식입니다.
  ###### search 메소드는 다음과 같습니다.
  
  ```
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
  ###### 예시로 검색창에 "am" 을 검색해 보겠습니다.
  ![capture3](https://user-images.githubusercontent.com/96767498/148048075-629eccbc-cc1e-49da-aed9-bbfd1407bee4.PNG)
  ###### "am" 이 이름에 들어가는 James 가 표시됩니다.
