import {Component, Input} from '@angular/core';

@Component({
	selector: 'panel',
	template:
`<div class="panel">
  <div class="panel-heading">
    <div *ngIf="icon"><img src="{{icon}}"/> </div>
    {{title}}
  </div>
  <div class="panel-body">
    <ng-content></ng-content>
  </div>
</div>`
})
export class PanelComponent {

@Input() title: String;
@Input() icon: String;

}

