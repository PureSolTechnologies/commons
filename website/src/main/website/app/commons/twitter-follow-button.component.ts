import {Component} from '@angular/core';

declare var twttr:any;

@Component({
	selector: 'twitter-follow-button',
	template: `
	  <a href="https://twitter.com/puresoltech" class="twitter-follow-button" data-show-count="false">Follow @puresoltech</a>
	  <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
	`  
})
export class TwitterFollowButtonComponent {
  ngAfterContentInit(){
    twttr.widgets.load(document.getElementById("container"));
  }
}
