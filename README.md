# random.org-wrapper

Java wrapper for working with the API [random.org](https://random.org)

### Example

````
RandomWrapper randomWrapper = new RandomWrapper(
"****-****-4fdf-8146-*********", //API_KEY
1, // n
0, // min
5, // max
false, // replacement
false); // debugging

Random random = randomWrapper.sendData();
System.out.println(random.getResult().getRandom().getData().get(0).getList());

Output: [2]