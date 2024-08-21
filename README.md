# AutoTesting - issues 

1. couldnt get a wait to work with the onetrust pop up so used thread.sleep (whoops)
2. the nav bar sometimes selected a page that doesnt have products such as 'the edit' - i understand a if statement or something is needed here to repeat the random nav click but struggled to get it to work after spliting into class files
3. likewise when selecting a random size, if the size if not avaliable or the product has one size - the test needs to repeat the nav click and pick a new item - again struggled to get the if statement to work after spliting into class files
4. The TestNG part - i get the concept and maybe i need to split my main test into a file per page - but when splitting on the one test i couldnt get values to pass between tests i.e. selectedSize or selectedItem, which i was using for the assertions
