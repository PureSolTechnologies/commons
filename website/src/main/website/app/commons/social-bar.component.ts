import {Component} from '@angular/core';

@Component({
	selector: 'social-bar',
	template:
`<twitter-follow-button></twitter-follow-button>
<tweet-button></tweet-button><br/>
<google-plus-addone-button></google-plus-addone-button>
<google-plus-follow-button></google-plus-follow-button>
<google-plus-share-button></google-plus-share-button><br/>
<facebook-like-button></facebook-like-button>
<facebook-follow-button></facebook-follow-button>`
})
export class SocialBarComponent {
}
