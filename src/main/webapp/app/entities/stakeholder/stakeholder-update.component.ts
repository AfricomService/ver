import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStakeholder, Stakeholder } from 'app/shared/model/stakeholder.model';
import { StakeholderService } from './stakeholder.service';

@Component({
  selector: 'jhi-stakeholder-update',
  templateUrl: './stakeholder-update.component.html',
})
export class StakeholderUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    stakeholderId: [null, [Validators.required]],
    name: [null, [Validators.required]],
    role: [null, [Validators.required]],
  });

  constructor(protected stakeholderService: StakeholderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ stakeholder }) => {
      this.updateForm(stakeholder);
    });
  }

  updateForm(stakeholder: IStakeholder): void {
    this.editForm.patchValue({
      id: stakeholder.id,
      stakeholderId: stakeholder.stakeholderId,
      name: stakeholder.name,
      role: stakeholder.role,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const stakeholder = this.createFromForm();
    if (stakeholder.id !== undefined) {
      this.subscribeToSaveResponse(this.stakeholderService.update(stakeholder));
    } else {
      this.subscribeToSaveResponse(this.stakeholderService.create(stakeholder));
    }
  }

  private createFromForm(): IStakeholder {
    return {
      ...new Stakeholder(),
      id: this.editForm.get(['id'])!.value,
      stakeholderId: this.editForm.get(['stakeholderId'])!.value,
      name: this.editForm.get(['name'])!.value,
      role: this.editForm.get(['role'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStakeholder>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
