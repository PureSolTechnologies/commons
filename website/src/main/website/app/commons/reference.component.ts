import {Component, Input} from '@angular/core';

@Component({
    selector: 'ref',
    template: `<a href="{{url}}" target="_blank"><ng-content></ng-content></a>`
})
export class ReferenceComponent {
        
  @Input() url: String;
  @Input() target: String;

}
