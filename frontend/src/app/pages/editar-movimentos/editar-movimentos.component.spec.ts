import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarMovimentosComponent } from './editar-movimentos.component';

describe('EditarMovimentosComponent', () => {
  let component: EditarMovimentosComponent;
  let fixture: ComponentFixture<EditarMovimentosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditarMovimentosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditarMovimentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
