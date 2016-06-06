package com.tomas.contact.model;


import java.time.Instant;
import java.time.MonthDay;

/**
 * Created by Tomas on 06/06/2016.
 */

public class Contact implements Comparable<Contact>
{
     private String firstName,
                    lastName,
                    phoneName,
                    address;
     private MonthDay birthday;
     private Instant instant;

     public Contact(String firstName, String lastName, String phoneName, String address, MonthDay birthday, Instant instant)
     {
          this.firstName = firstName;
          this.lastName = lastName;
          this.phoneName = phoneName;
          this.address = address;
          this.birthday = birthday;
          this.instant = instant;
     }
     public Contact()
     {

     }

     public String getFirstName() {
          return firstName;
     }

     public void setFirstName(String firstName) {
          this.firstName = firstName;
     }

     public String getLastName() {
          return lastName;
     }

     public void setLastName(String lastName) {
          this.lastName = lastName;
     }

     public String getPhoneName() {
          return phoneName;
     }

     public void setPhoneName(String phoneName) {
          this.phoneName = phoneName;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public MonthDay getBirthday() {
          return birthday;
     }

     public void setBirthday(MonthDay birthday) {
          this.birthday = birthday;
     }

     public Instant getInstant() {
          return instant;
     }

     public void setInstant(Instant instant) {
          this.instant = instant;
     }

     @Override
     public int compareTo(Contact other)
     {
          int last = lastName.compareTo(other.lastName);
          if(last == 0)
               return firstName.compareTo(other.firstName);
          return last;
     }
}
