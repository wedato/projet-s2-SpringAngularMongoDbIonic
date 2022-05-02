import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupeTpComponent } from './groupe-tp.component';

describe('GroupeTpComponent', () => {
  let component: GroupeTpComponent;
  let fixture: ComponentFixture<GroupeTpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupeTpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupeTpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
