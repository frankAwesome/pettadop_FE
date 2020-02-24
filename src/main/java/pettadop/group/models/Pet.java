package pettadop.group.models;

public class Pet{
    

    public long id;
    public String petName;
    public String breed;
    public String sex;
    public int age;
    public String story;
    public String storyTwo;
    public boolean adopted;
    public String adoptedList;
    public String imageUrlOne;
    public String imageUrlTwo;
    public String imageUrlThree;
    public String location;
    public String extraOne;
    public String extraTwo;
    public String extraThree;

    public String getPetName()
    {
        return this.petName;
    }
    public void setPetName(String petName)
    {
        this.petName = petName;;
    }
    public String getBreed()
    {
        return this.breed;
    }
    public void setBreed(String breed)
    {
        this.breed = breed;;
    }
    public String getSex()
    {
        return this.sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;;
    }
    public int getAge()
    {
        return this.age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public String getStory()
    {
        return this.story;
    }
    public void setStory(String story)
    {
        this.story = story;;
    }
    public String getStoryTwo()
    {
        return this.storyTwo;
    }
    public void setStoryTwo(String storyTwo)
    {
        this.storyTwo = storyTwo;;
    }
    public String getImageUrlOne()
    {
        return this.imageUrlOne;
    }
    public void setImageUrlOne(String imageUrlOne)
    {
        this.imageUrlOne = imageUrlOne;
    }
    public String getImageUrlTwo()
    {
        return this.imageUrlTwo;
    }
    public void setImageUrlTwo(String imageUrlTwo)
    {
        this.imageUrlTwo = imageUrlTwo;
    }
    public String getImageUrlThree()
    {
        return this.imageUrlThree;
    }
    public void setImageUrlThree(String imageUrlThree)
    {
        this.imageUrlThree = imageUrlThree;
    }
    public String getLocation()
    {
        return this.location;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

}