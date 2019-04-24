import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'AngularTemplate';

  private data = ['1','2','3','4'];

  constructor(){
    console.log(...this.data); //SPREAD OPERATOR 
    /*Spread syntax allows an iterable such as an array expression or string to be expanded in places where zero or more arguments (for function calls) or elements (for array literals) are expected, or an object expression to be expanded in places where zero or more key-value pairs (for object literals) are expected.*/
    this.msg(); // Message: 🤡 <-- after 2 seconds
  }



   scaryClown() {
    return new Promise(resolve => {
      setTimeout(() => {
        resolve('🤡');
      }, 2000);
    });
  }
  
  async msg() {
    const msg = await this.scaryClown(); // await is a new operator used to wait for a promise to resolve or reject.
    console.log('Message:', msg);// THIS IS GOING TO WAIT UNTIL THE SCARY CLOWN METHOD IS COMPLETED.
  }
  
 
}
