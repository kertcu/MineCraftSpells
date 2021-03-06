package com.gmail.spraetz.spells;

import com.gmail.spraetz.plugin.MineCraftSpells;
import org.bukkit.entity.Damageable;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by spraetz on 3/1/14.
 */
public class HealOther extends TouchSpell {

    public HealOther(EntityDamageByEntityEvent event, MineCraftSpells plugin) {
        super(event, plugin);
    }

    @Override
    public Boolean spellEffects(EntityDamageByEntityEvent event, String spellName) {

        if(event.getEntity() instanceof Damageable){
            Damageable target = (Damageable)event.getEntity();

            Double maxHealth = target.getMaxHealth();
            Double currentHealth = target.getHealth();
            Double newHealth = currentHealth +
                    (maxHealth * .01 * getSetting(spellName, "percent_of_max_health", Integer.class));

            if(newHealth > maxHealth){
                target.setHealth(maxHealth);
            }
            else{
                target.setHealth(newHealth);
            }

            plugin.getEffects().playVisual(plugin, target.getLocation(), "heart", .2F, .2F, .1F, 3, 2F);
            return true;
        }
        else{
            return false;
        }
    }
}
