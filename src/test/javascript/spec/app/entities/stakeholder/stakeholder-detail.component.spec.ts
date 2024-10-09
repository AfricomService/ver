import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { VerprojectsTestModule } from '../../../test.module';
import { StakeholderDetailComponent } from 'app/entities/stakeholder/stakeholder-detail.component';
import { Stakeholder } from 'app/shared/model/stakeholder.model';

describe('Component Tests', () => {
  describe('Stakeholder Management Detail Component', () => {
    let comp: StakeholderDetailComponent;
    let fixture: ComponentFixture<StakeholderDetailComponent>;
    const route = ({ data: of({ stakeholder: new Stakeholder(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [VerprojectsTestModule],
        declarations: [StakeholderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(StakeholderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StakeholderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load stakeholder on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.stakeholder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
