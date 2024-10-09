import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { VerprojectsTestModule } from '../../../test.module';
import { StakeholderUpdateComponent } from 'app/entities/stakeholder/stakeholder-update.component';
import { StakeholderService } from 'app/entities/stakeholder/stakeholder.service';
import { Stakeholder } from 'app/shared/model/stakeholder.model';

describe('Component Tests', () => {
  describe('Stakeholder Management Update Component', () => {
    let comp: StakeholderUpdateComponent;
    let fixture: ComponentFixture<StakeholderUpdateComponent>;
    let service: StakeholderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [VerprojectsTestModule],
        declarations: [StakeholderUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(StakeholderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StakeholderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StakeholderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Stakeholder(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Stakeholder();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
