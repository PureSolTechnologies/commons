package com.puresoltechnologies.commons.math.money;

import com.puresoltechnologies.commons.math.CompoundSIUnitWithMoney;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;

/**
 * This class is for describing physical values. Additionally, this object takes
 * a money dimension.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PhysicalParameterWithMoney<T> extends ParameterWithArbitraryUnit<T> {

    private static final long serialVersionUID = -3381374301031154841L;

    private final CompoundSIUnitWithMoney unit;

    public PhysicalParameterWithMoney(String name, CompoundSIUnitWithMoney unit, LevelOfMeasurement levelOfMeasurement,
	    String description, Class<T> type) {
	super(name, unit.toString(), levelOfMeasurement, description, type);
	this.unit = unit;
    }

    public final CompoundSIUnitWithMoney getPhysicalUnit() {
	return unit;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((unit == null) ? 0 : unit.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	PhysicalParameterWithMoney other = (PhysicalParameterWithMoney) obj;
	if (unit == null) {
	    if (other.unit != null)
		return false;
	} else if (!unit.equals(other.unit))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer(getName());
	if (unit != null) {
	    String unitString = getUnit();
	    if ((unitString != null) && (!unitString.isEmpty())) {
		buffer.append(" [").append(unitString).append("]");
	    }
	}
	String description = getDescription();
	if ((description != null) && (!description.isEmpty())) {
	    buffer.append(" (").append(description).append(")");
	}
	LevelOfMeasurement levelOfMeasurement = getLevelOfMeasurement();
	if (levelOfMeasurement != null) {
	    buffer.append(" {").append(levelOfMeasurement).append("}");
	}
	return buffer.toString();
    }
}
